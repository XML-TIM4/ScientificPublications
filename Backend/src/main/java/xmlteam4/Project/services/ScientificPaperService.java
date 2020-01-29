package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xmlteam4.Project.businessprocess.*;
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

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static xmlteam4.Project.utilities.exist.XUpdateTemplate.TARGET_NAMESPACE;

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


    public String getScientificPaperXML(String id) throws Exception {
        String paper = scientificPaperRepository.findOne(id);

        if (paper == null) {
            throw new NotFoundException(String.format("Scientific paper with id %s is not found", id));
        }
        return paper;
    }

    public String getScientificPaperHTML(String id) throws Exception {
        return xslTransformer.generateHTML(getScientificPaperXML(id), scientificPaperToHTML);
    }

    public InputStreamResource getScientificPaperPDF(String id) throws Exception {
        return new InputStreamResource(new ByteArrayInputStream(xslTransformer.generatePDF(getScientificPaperXML(id),
                scientificPaperToPDF).toByteArray()));
    }

    public String createScientificPaper(String xml) throws Exception {
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        if (document == null)
            throw new DocumentParsingFailedException("Document is not valid");
        else if (!checkAbstract(document))
            throw new DocumentParsingFailedException("Invalid abstract titles");
        else if (!checkIfCreatorIsAuthor(document))
            throw new UnauthorizedException("In order to create a scientific paper you have to be an author");

        // set ids
        String id = idGenerator.createID();
        setIDs(id, document);

        // set metadata
        document.getElementsByTagName("received").item(0).setTextContent(dateTimeFormatter.format(LocalDateTime.now()));
        document.getElementsByTagName("status").item(0)
                .setTextContent(ScientificPaperStatus.UPLOADED.toString());

        // transform to rdfa
        String rdfa = xslTransformer.generateHTML(documentXMLTransformer.toXMLString(document),
                scientificPaperToRDFa);

        // transform to rdf/xml
        String rdf = xslTransformer.generateHTML(rdfa, grddl);

        // create new business process for document
        businessProcessService.createBusinessProcess(id);

        // create graph and save to rdf database
        sparqlService.createGraph("/scientific-papers/" + id, rdf);

        // save rdfa to xml database
        return scientificPaperRepository.create(id, rdfa);
    }

    public String reviseScientificPaper(String id, String xml) throws Exception {
        // check if scientific paper can be revised
        TBusinessProcess businessProcess = businessProcessService.findById(id);

        if (businessProcess == null)
            throw new EntityNotFoundException("Cannot revise nonexistent document");
        else if (!getActiveCycle(businessProcess).getStatus().equals(ReviewCycleStatus.REVISE.toString()))
            throw new BusinessProcessException("Cannot revise rejected, accepted, withdrawn or already revised " +
                    "scientific paper");

        // build and validate new scientific paper version
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        if (document == null)
            throw new DocumentParsingFailedException("Document is not valid");
        else if (!checkAbstract(document))
            throw new DocumentParsingFailedException("Invalid abstract titles");
        else if (!checkIfCreatorIsAuthor(document))
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
        String rdf = xslTransformer.generateHTML(newXml, grddl);
        String graphName = "/scientific-papers/" + id;
        sparqlService.deleteGraph(graphName);
        sparqlService.createGraph(graphName, rdf);

        return scientificPaperRepository.update(id, newXml);
    }

    public Boolean withdrawScientificPaper(String id) throws Exception {
        String paper = getScientificPaperXML(id);

        if (paper == null)
            throw new EntityNotFoundException("Cannot withdrawn nonexistent scientific paper");

        Document document = domParser.buildDocument(paper, scientificPaperSchemaPath);

        document.getElementsByTagName("status").item(0)
                .setTextContent(ScientificPaperStatus.WITHDRAWN.toString());
        String rdfa = documentXMLTransformer.toXMLString(document);
        String rdf = xslTransformer.generateHTML(rdfa, grddl);

        String graphName = "/scientific-papers/" + id;
        sparqlService.deleteGraph(graphName);
        sparqlService.createGraph(graphName, rdf);

        TBusinessProcess businessProcess = businessProcessService.findById(id);
        TReviewCycle activeCycle = getActiveCycle(businessProcess);
        activeCycle.setStatus(ReviewCycleStatus.WITHDRAWN.toString());
        businessProcessService.updateBusinessProcess(businessProcess);

        scientificPaperRepository.update(id, documentXMLTransformer.toXMLString(document));
        return true;
    }

    private void setIDs(String id, Document document) throws BadParametersException {
        String paperId = TARGET_NAMESPACE + "/scientific-papers/" + id;
        document.getDocumentElement().setAttribute("id", paperId);

        NodeList authors = document.getElementsByTagName("author");

        idGenerator.generateUserIDs(authors);


        Node abstr = document.getElementsByTagName("abstract").item(0);
        idGenerator.generateChildlessElementID(abstr, paperId + "/abstract", "abstract");

        NodeList sections = document.getElementsByTagName("section");
        for (int i = 0; i < sections.getLength(); ++i) {
            idGenerator.generateSectionID(sections.item(i), paperId + "/sections/" + (i + 1));
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
    private boolean checkIfCreatorIsAuthor(Document document) {
        NodeList authors = document.getElementsByTagName("author");
        String creatorEmail = ((TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getEmail();

        for (int i = 0; i < authors.getLength(); i++) {
            // check if author's email equals creator's email
            if (authors.item(i).getChildNodes().item(1).getTextContent().equals(creatorEmail))
                return true;
        }

        return false;
    }

    private TPhase getActivePhase(TBusinessProcess businessProcess) {
        TReviewCycle activeCycle = getActiveCycle(businessProcess);
        return activeCycle.getPhases().getPhase().get(activeCycle.getPhases().getPhase().size() - 1);
    }

    private TReviewCycle getActiveCycle(TBusinessProcess businessProcess) {
        return (TReviewCycle) businessProcess.getReviewCycles().getReviewCycle()
                .get(businessProcess.getReviewCycles().getReviewCycle().size() - 1);
    }
}
