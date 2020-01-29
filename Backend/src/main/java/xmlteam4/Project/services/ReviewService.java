package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xmlteam4.Project.exceptions.CRUDServiceException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.exceptions.TransformationException;
import xmlteam4.Project.model.ScientificPaperStatus;
import xmlteam4.Project.repositories.ReviewRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.sparql.SparqlService;
import xmlteam4.Project.utilities.transformers.documentxmltransformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import java.io.ByteArrayInputStream;

import static xmlteam4.Project.utilities.exist.XUpdateTemplate.TARGET_NAMESPACE;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    @Autowired
    private XSLTransformer xslTransformer;

    @Autowired
    private IDGenerator idGenerator;

    @Autowired
    private ScientificPaperService scientificPaperService;

    @Autowired
    private SparqlService sparqlService;

    @Value("${review-schema-path}")
    private String reviewSchemaPath;

    @Value("${scientific-paper-schema-path}")
    private String scientificPaperSchemaPath;

    @Value("${grddl-xslt}")
    private String grddl;

    @Value("data/xsl/xsl-t/ReviewToRDFa.xsl")
    private String reviewToRDFa;

    @Value("data/xsl/xsl-t/ReviewToHTML.xsl")
    private String reviewToHTML;

    @Value("data/xsl/xsl-fo/ReviewToPDF.xsl")
    private String reviewToPDF;


    public String getReviewXML(String id) throws NotFoundException, RepositoryException{
        String review = reviewRepository.findOne(id);
        if (review == null) {
            throw new NotFoundException("Review not found");
        }
        return review;
    }


    public String getReviewHTML(String id) throws Exception {
        return xslTransformer.generateHTML(getReviewXML(id),reviewToHTML);
    }

    public InputStreamResource getReviewPDF(String id) throws Exception{
        return new InputStreamResource(new ByteArrayInputStream(xslTransformer.generatePDF(getReviewXML(id),
                reviewToPDF).toByteArray()));
    }


    public String create(String scientificPaperId, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, reviewSchemaPath);

        String id = idGenerator.createID();
        String fullUrl = TARGET_NAMESPACE + "/reviews/" + id;
        document.getDocumentElement().setAttribute("id", fullUrl);

        NodeList reviewers = document.getElementsByTagName("reviewer");
        idGenerator.generateUserIDs(reviewers);

        String scientificPaper = scientificPaperService.getScientificPaperXML(scientificPaperId);

        if(scientificPaper == null){
            throw new CRUDServiceException("Scientific paper doesn't exist.");
        }

        Document scientificDocument = domParser.buildDocument(scientificPaper,scientificPaperSchemaPath);

        if(!scientificDocument.getElementsByTagName("status").item(0).getTextContent().equals(ScientificPaperStatus.UPLOADED.toString())){
            throw new CRUDServiceException("Status of paper is not uploaded!");
        }

        document.getElementsByTagName("scientific-paper-reference").item(0).setTextContent(scientificPaperId);

       // String newXml = documentXMLTransformer.toXMLString(document);

        String rdfa = xslTransformer.generateHTML(documentXMLTransformer.toXMLString(document),reviewToRDFa);

        String rdf = xslTransformer.generateHTML(rdfa, grddl);

        sparqlService.createGraph("/reviews/"+id, rdf);

        return reviewRepository.create(id, rdfa);
    }
}
