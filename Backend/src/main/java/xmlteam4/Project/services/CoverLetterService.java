package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import xmlteam4.Project.exceptions.CRUDServiceException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.exceptions.TransformationException;
import xmlteam4.Project.model.ScientificPaperStatus;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.repositories.CoverLetterRepository;
import xmlteam4.Project.repositories.UserRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.sparql.SparqlService;
import xmlteam4.Project.utilities.transformers.documentxmltransformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import java.io.ByteArrayInputStream;
import static xmlteam4.Project.utilities.exist.XUpdateTemplate.TARGET_NAMESPACE;

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
    private ScientificPaperService scientificPaperService;

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
        return xslTransformer.generateHTML(getCoverLetterXML(id),coverLetterToHTML);
    }

    public InputStreamResource getCoverLetterPDF(String id) throws Exception {
        return new InputStreamResource(new ByteArrayInputStream(xslTransformer.generatePDF(getCoverLetterXML(id),
                coverLetterToPDF).toByteArray()));
    }

    public String create(String scientificPaperId, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, coverLetterSchemaPath);

        String id = idGenerator.createID();
        document.getDocumentElement().setAttribute("id", id);

        NodeList paragraphs = document.getElementsByTagName("paragraph");
        for (int i = 0; i < paragraphs.getLength(); ++i) {
            idGenerator.generateParagraphID(paragraphs.item(i), id + "/paragraphs/" + (i + 1));
        }

        NodeList authors = document.getElementsByTagName("author");
        idGenerator.generateUserIDs(authors);

        NodeList editors = document.getElementsByTagName("editor");
        idGenerator.generateUserIDs(editors);

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

        String rdfa = xslTransformer.generateHTML(documentXMLTransformer.toXMLString(document),coverLetterToRDFa);

        String rdf = xslTransformer.generateHTML(rdfa, grddl);

        sparqlService.createGraph("/cover-letters/"+id, rdf);

        return coverLetterRepository.create(id, rdfa);
    }

}
