package xmlteam4.Project.services;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xmlteam4.Project.DTOs.SearchDTO;
import xmlteam4.Project.DTOs.SearchResultDTO;
import xmlteam4.Project.businessprocess.DocumentType;
import xmlteam4.Project.businessprocess.ReviewCycleStatus;
import xmlteam4.Project.businessprocess.TBusinessProcess;
import xmlteam4.Project.businessprocess.TReviewCycle;
import xmlteam4.Project.exceptions.*;
import xmlteam4.Project.model.ScientificPaperAbstractTitles;
import xmlteam4.Project.model.ScientificPaperStatus;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.repositories.ScientificPaperRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.sparql.SparqlService;
import xmlteam4.Project.utilities.transformers.documentxmltransformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import javax.mail.MessagingException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScientificPaperService {
    @Autowired
    private ScientificPaperRepository scientificPaperRepository;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    @Autowired
    private XSLTransformer xslTransformer;

    @Autowired
    private IDGenerator idGenerator;

    @Autowired
    private SparqlService sparqlService;

    @Autowired
    private BusinessProcessService businessProcessService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${scientific-paper-schema-path}")
    private String scientificPaperSchemaPath;

    @Value("${grddl-xslt}")
    private String grddl;

    @Value("${scientific-paper-rdfa-xslt}")
    private String scientificPaperToRDFa;

    @Value("${scientific-paper-html-xslt}")
    private String scientificPaperToHTML;

    @Value("${scientific-paper-pdf-xslfo}")
    private String scientificPaperToPDF;


    public String getScientificPaperXML(String id) throws RepositoryException {
        return scientificPaperRepository.findOne(id);
    }

    public String getScientificPaperHTML(String id) throws RepositoryException, TransformationException {
        return xslTransformer.generateXML(getScientificPaperXML(id), scientificPaperToHTML);
    }

    public InputStreamResource getScientificPaperPDF(String id) throws RepositoryException, TransformationException {
        return new InputStreamResource(new ByteArrayInputStream(xslTransformer.generatePDF(getScientificPaperXML(id),
                scientificPaperToPDF).toByteArray()));
    }

    public String createScientificPaper(String xml) throws DocumentParsingFailedException, UnauthorizedException, BadParametersException, TransformerException, TransformationException, RepositoryException, EntityAlreadyExistsException, MessagingException {
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        if (!checkAbstract(document))
            throw new DocumentParsingFailedException("Invalid abstract titles");
        else if (!checkIfCreatorIsAuthor(xml))
            throw new UnauthorizedException("In order to create a scientific paper you have to be an author");

        // set ids
        String id = idGenerator.createID();
        setIDs(id, document);

        // set metadata
        document.getElementsByTagName("received").item(0).setTextContent(dateTimeFormatter.format(LocalDateTime.now()));
        document.getElementsByTagName("status").item(0)
                .setTextContent(ScientificPaperStatus.UPLOADED.toString());

        // transform to rdfa
        String rdfa = xslTransformer.generateXML(documentXMLTransformer.toXMLString(document),
                scientificPaperToRDFa);

        // transform to rdf/xml
        String rdf = xslTransformer.generateXML(rdfa, grddl);

        // create new business process for document
        businessProcessService.createBusinessProcess(id);

        // create graph and save to rdf database
        sparqlService.createGraph("/scientific-papers/" + id, rdf);

        // notify user via email
        TUser loggedIn = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notificationService.notifyUser(loggedIn, id, DocumentType.SCIENTIFIC_PAPER, "Your scientific paper has been " +
                "successfully submitted.");

        // save rdfa to xml database
        return scientificPaperRepository.create(id, rdfa);
    }

    public String reviseScientificPaper(String id, String xml) throws RepositoryException, BusinessProcessException, DocumentParsingFailedException, UnauthorizedException, BadParametersException, TransformerException, TransformationException, MessagingException {
        // check if scientific paper can be revised
        TBusinessProcess businessProcess = businessProcessService.findById(id);

        if (!businessProcessService.getActiveCycle(businessProcess).getStatus()
                .equals(ReviewCycleStatus.REVISE.toString()))
            throw new BusinessProcessException("Cannot revise rejected, accepted, withdrawn or already revised " +
                    "scientific paper");

        // build and validate new scientific paper version
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        if (!checkAbstract(document))
            throw new DocumentParsingFailedException("Invalid abstract titles");
        else if (!checkIfCreatorIsAuthor(xml))
            throw new UnauthorizedException("In order to revise a scientific paper you have to be an author");

        // set new id's and metadata
        setIDs(id, document);
        document.getElementsByTagName("received").item(0)
                .setTextContent(businessProcess.getCreated().toXMLFormat());
        document.getElementsByTagName("revised").item(0)
                .setTextContent(dateTimeFormatter.format(LocalDateTime.now()));
        document.getElementsByTagName("status").item(0)
                .setTextContent(ScientificPaperStatus.REVISION.toString());
        double version = Double
                .parseDouble(document.getElementsByTagName("version").item(0).getTextContent()) + 1.0;
        document.getElementsByTagName("version").item(0).setTextContent(Double.toString(version));

        // start new business cycle and update business process
        TReviewCycle newCycle = businessProcessService.createNewReviewCycle(id);
        businessProcess.getReviewCycles().getReviewCycle().add(newCycle);
        businessProcessService.updateBusinessProcess(businessProcess);

        // deal with rdf
        String newXml = documentXMLTransformer.toXMLString(document);
        String rdf = xslTransformer.generateXML(newXml, grddl);
        String graphName = "/scientific-papers/" + id;
        sparqlService.deleteGraph(graphName);
        sparqlService.createGraph(graphName, rdf);

        // notify user via email
        TUser loggedIn = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notificationService.notifyUser(loggedIn, id, DocumentType.SCIENTIFIC_PAPER, "Your scientific paper has been " +
                "successfully revised.");

        return scientificPaperRepository.update(id, newXml);
    }

    public Boolean withdrawScientificPaper(String id) throws RepositoryException, BusinessProcessException, DocumentParsingFailedException, TransformerException, TransformationException, MessagingException {
        String paper = getScientificPaperXML(id);

        TBusinessProcess businessProcess = businessProcessService.findById(id);

        // cycle has to be in progress in order to withdraw
        if (!businessProcessService.getActiveCycle(businessProcess).getStatus()
                .equals(ReviewCycleStatus.PENDING.toString()))
            throw new BusinessProcessException("Cannot withdraw scientific paper if review cycle is finished");

        Document document = domParser.buildDocument(paper, scientificPaperSchemaPath);

        // set status to withdrawn
        document.getElementsByTagName("status").item(0)
                .setTextContent(ScientificPaperStatus.WITHDRAWN.toString());
        String rdfa = documentXMLTransformer.toXMLString(document);
        String rdf = xslTransformer.generateXML(rdfa, grddl);

        // delete old metadata and create new
        String graphName = "/scientific-papers/" + id;
        sparqlService.deleteGraph(graphName);
        sparqlService.createGraph(graphName, rdf);

        // update active cycle
        TReviewCycle activeCycle = businessProcessService.getActiveCycle(businessProcess);
        activeCycle.setStatus(ReviewCycleStatus.WITHDRAWN.toString());
        businessProcessService.updateBusinessProcess(businessProcess);

        // notify user via email
        TUser loggedIn = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notificationService.notifyUser(loggedIn, id, DocumentType.SCIENTIFIC_PAPER, "Your scientific paper has been " +
                "successfully withdrawn.");

        // save rdfa in exist
        scientificPaperRepository.update(id, documentXMLTransformer.toXMLString(document));
        return true;
    }

    public Boolean decideForPaper(String scientificPaperId, ScientificPaperStatus decision) throws RepositoryException,
            BusinessProcessException, DocumentParsingFailedException, TransformerException, TransformationException, MessagingException {
        String paper = getScientificPaperXML(scientificPaperId);

        TBusinessProcess businessProcess = businessProcessService.findById(scientificPaperId);

        // decision can only be made after reviewers have finished their task
        if (!businessProcessService.getActiveCycle(businessProcess).getStatus()
                .equals(ReviewCycleStatus.REVIEWED.toString()))
            throw new BusinessProcessException("Cannot decide on scientific paper if reviews are not done");

        Document document = domParser.buildDocument(paper, scientificPaperSchemaPath);

        // set status accordingly
        document.getElementsByTagName("status").item(0)
                .setTextContent(decision.toString());
        document.getElementsByTagName("status").item(0)
                .getAttributes().getNamedItem("content").setTextContent(decision.toString());

        // extract paper creator in order to send notifications
        String paperCreatorId = businessProcessService.getPaperCreatorId(scientificPaperId);
        TUser creator = userService.findOneById(paperCreatorId);

        // send notification
        if (decision.equals(ScientificPaperStatus.ACCEPTED)) {
            // set accepted date
            document.getElementsByTagName("accepted").item(0)
                    .setTextContent(dateTimeFormatter.format(LocalDateTime.now()));
            notificationService.notifyUser(creator, scientificPaperId, DocumentType.SCIENTIFIC_PAPER, "Your " +
                    "scientific paper has been accepted.");
        } else if (decision.equals(ScientificPaperStatus.REJECTED)) {
            notificationService.notifyUser(creator, scientificPaperId, DocumentType.SCIENTIFIC_PAPER, "Your " +
                    "scientific paper has been rejected.");
        } else {
            notificationService.notifyUser(creator, scientificPaperId, DocumentType.SCIENTIFIC_PAPER, "Your " +
                    "scientific paper has been requested for revision.");
        }

        String rdfa = documentXMLTransformer.toXMLString(document);
        String rdf = xslTransformer.generateXML(rdfa, grddl);

        // create new metadata
        String graphName = "/scientific-papers/" + scientificPaperId;
        sparqlService.deleteGraph(graphName);
        sparqlService.createGraph(graphName, rdf);

        // update business process
        TReviewCycle activeCycle = businessProcessService.getActiveCycle(businessProcess);
        activeCycle.setStatus(decision.toString());
        businessProcessService.updateBusinessProcess(businessProcess);

        // save rdfa to exist
        scientificPaperRepository.update(scientificPaperId, documentXMLTransformer.toXMLString(document));

        return true;
    }

    public Pair<List<String>, String> extractDataForSelectionOfReviewers(Document scientificPaper) {
        List<String> authorIds = new ArrayList<>();

        NodeList authors = scientificPaper.getElementsByTagName("author");

        for (int i = 0; i < authors.getLength(); i++) {
            authorIds.add(authors.item(i).getChildNodes().item(3).getTextContent());
        }

        String keywords =
                scientificPaper.getElementsByTagName("keywords").item(0).getAttributes().getNamedItem("content")
                        .getTextContent();

        return new Pair<>(authorIds, keywords);
    }

    public SearchResultDTO basicScientificPaperSearch(SearchDTO searchDTO) throws RepositoryException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated())
            return scientificPaperRepository.basicSearch(searchDTO.getText(),
                    ((TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        else
            return scientificPaperRepository.basicSearch(searchDTO.getText());
    }

    public SearchResultDTO basicScientificPaperSearchEditor(SearchDTO searchDTO) throws RepositoryException {
        return scientificPaperRepository.basicSearchEditor(searchDTO.getText());
    }

    public SearchResultDTO advancedScientificPaperSearch(SearchDTO searchDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return scientificPaperRepository.advancedSearch(searchDTO,
                    ((TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        } else
            return scientificPaperRepository.advancedSearch(searchDTO);
    }

    public SearchResultDTO advancedScientificPaperSearchEditor(SearchDTO searchDTO) {

        return scientificPaperRepository.advancedSearchEditor(searchDTO);
    }

    public String getScientificPaperMetadataAsJSONLD(String id) throws RepositoryException {
        String paper = scientificPaperRepository.findOne(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("content", paper);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "http://rdf-translator.appspot.com/convert/rdfa/json-ld/content";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForEntity(url, request, String.class).getBody();
    }

    public String getScientificPaperMetadataAsTurtle(String id) throws RepositoryException {
        String paper = scientificPaperRepository.findOne(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("content", paper);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "http://rdf-translator.appspot.com/convert/rdfa/nt/content";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForEntity(url, request, String.class).getBody();
    }

    private void setIDs(String id, Document document) throws BadParametersException {
        document.getDocumentElement().setAttribute("id", id);

        NodeList authors = document.getElementsByTagName("author");

        idGenerator.generateUserIDs(authors);

        Node abstr = document.getElementsByTagName("abstract").item(0);
        idGenerator.generateChildlessElementID(abstr, id + "/abstract", "abstract");

        NodeList sections = document.getElementsByTagName("section");
        for (int i = 0; i < sections.getLength(); ++i) {
            idGenerator.generateSectionID(sections.item(i), id + "/sections/" + (i + 1));
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean checkAbstract(Document document) {
        ArrayList<String> abstractTitles = new ArrayList<>();
        NodeList abstractItems = document.getElementsByTagName("abstract-item");

        for (int i = 0; i < abstractItems.getLength(); i++) {
            abstractTitles.add(abstractItems.item(i).getAttributes().getNamedItem("title").getTextContent());
        }

        Set<String> uniqueTitles = new HashSet<>(abstractTitles);

        if (uniqueTitles.size() != abstractTitles.size()) {
            return false;
        }

        for (ScientificPaperAbstractTitles title : ScientificPaperAbstractTitles.values()) {
            if (title.isMandatory() && !uniqueTitles.contains(title.toString())) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean checkIfCreatorIsAuthor(String xml) {
        String creatorEmail = ((TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getEmail();
        return xml.contains(creatorEmail);
    }
}
