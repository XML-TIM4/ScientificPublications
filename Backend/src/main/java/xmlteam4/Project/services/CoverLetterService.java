package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import xmlteam4.Project.businessprocess.*;
import xmlteam4.Project.exceptions.BusinessProcessException;
import xmlteam4.Project.exceptions.DocumentParsingFailedException;
import xmlteam4.Project.exceptions.EntityNotFoundException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.repositories.CoverLetterRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.sparql.SparqlService;
import xmlteam4.Project.utilities.transformers.documentxmltransformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import java.io.ByteArrayInputStream;

@Service
public class CoverLetterService {
    @Autowired
    private SparqlService sparqlService;

    @Autowired
    private CoverLetterRepository coverLetterRepository;

    @Autowired
    private XSLTransformer xslTransformer;

    @Autowired
    private IDGenerator idGenerator;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    @Autowired
    private BusinessProcessService businessProcessService;

    @Value("${cover-letter-schema-path}")
    private String coverLetterSchemaPath;

    @Value("${scientific-paper-schema-path}")
    private String scientificPaperSchemaPath;

    @Value("${grddl-xslt}")
    private String grddl;

    @Value("data/xsl/xsl-t/CoverLetterToRDFa.xsl")
    private String coverLetterToRDFa;

    @Value("data/xsl/xsl-t/CoverLetterToHTML.xsl")
    private String coverLetterToHTML;

    @Value("data/xsl/xsl-fo/CoverLetterToPDF.xsl")
    private String coverLetterToPDF;

    public String getCoverLetterXML(String id) throws NotFoundException, RepositoryException {
        String coverLetter = coverLetterRepository.findOne(id);
        if (coverLetter == null) {
            throw new NotFoundException("Cover letter not found");
        }
        return coverLetter;
    }

    public String getCoverLetterHTML(String id) throws Exception {
        return xslTransformer.generateXML(getCoverLetterXML(id), coverLetterToHTML);
    }

    public InputStreamResource getCoverLetterPDF(String id) throws Exception {
        return new InputStreamResource(new ByteArrayInputStream(xslTransformer.generatePDF(getCoverLetterXML(id),
                coverLetterToPDF).toByteArray()));
    }

    public String create(String xml) throws Exception {
        Document document = domParser.buildDocument(xml, coverLetterSchemaPath);

        // parsing failed, document is not valid
        if (document == null)
            throw new DocumentParsingFailedException("Document is not valid");

        String scientificPaperId = document.getElementsByTagName("scientific-paper-reference").item(0)
                .getTextContent();

        // check if cover letter has set scientific paper id
        if (scientificPaperId == null)
            throw new EntityNotFoundException("Cannot create cover letter for nonexistent scientific paper");

        TBusinessProcess businessProcess = businessProcessService.findById(scientificPaperId);

        // business proces is null = scientific paper does not exist
        if (businessProcess == null)
            throw new EntityNotFoundException("Cannot create cover letter for nonexistent scientific paper");

        TReviewCycle reviewCycle = businessProcessService.getActiveCycle(businessProcess);

        // check if review cycle is pending
        if (!reviewCycle.getStatus().equals(ReviewCycleStatus.PENDING.toString()))
            throw new BusinessProcessException("Cannot create cover letter for finished review cycle");

        TPhase activePhase = businessProcessService.getActivePhase(reviewCycle);

        // phase has to be submitted
        if (!activePhase.getTitle().equals(PhaseTitle.SUBMITTED.toString()))
            throw new BusinessProcessException("Cannot create cover letter at this phase of review cycle");

        TActorTask createCoverLetterTask = businessProcessService.getTaskByDocumentType(activePhase,
                DocumentType.COVER_LETTER);

        // cannot create cover letter if task is already finished
        if (createCoverLetterTask.isFinished())
            throw new BusinessProcessException("Cover letter is already created");

        TUser loggedInUser = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // cannot create cover letter if user is not also the creator of scientific paper
        if (!createCoverLetterTask.getUserId().equals(loggedInUser.getId()))
            throw new BusinessProcessException("Only user that uploaded scientific paper can upload cover letter");

        String id = idGenerator.createID();

        // set task to finished
        createCoverLetterTask.setDocumentId(id);
        createCoverLetterTask.setFinished(true);

        // update business process
        businessProcessService.updateBusinessProcess(businessProcess);

        // set metadata and id's
        document.getDocumentElement().setAttribute("id", id);

        NodeList paragraphs = document.getElementsByTagName("paragraph");
        for (int i = 0; i < paragraphs.getLength(); ++i) {
            idGenerator.generateParagraphID(paragraphs.item(i), id + "/paragraphs/" + (i + 1));
        }

        NodeList authors = document.getElementsByTagName("author");
        idGenerator.generateUserIDs(authors);

        // deal with rdf
        String rdfa = xslTransformer.generateXML(documentXMLTransformer.toXMLString(document), coverLetterToRDFa);
        String rdf = xslTransformer.generateXML(rdfa, grddl);
        sparqlService.createGraph("/cover-letters/" + id, rdf);

        return coverLetterRepository.create(id, rdfa);
    }

    public String getCoverLetterId(String scientificPaperId) throws RepositoryException {
        return coverLetterRepository.findOneByPaperId(scientificPaperId);
    }
}
