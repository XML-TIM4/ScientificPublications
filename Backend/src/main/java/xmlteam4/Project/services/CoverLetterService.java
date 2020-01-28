package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.exceptions.TransformationException;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.repositories.CoverLetterRepository;
import xmlteam4.Project.repositories.UserRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformers.documentxmltransformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import static xmlteam4.Project.utilities.exist.XUpdateTemplate.TARGET_NAMESPACE;

@Service
public class CoverLetterService {

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

    @Value("${cover-letter-schema-path}")
    private String coverLetterSchemaPath;

    public String findOne(String id) throws NotFoundException, RepositoryException {
        String coverLetter = coverLetterRepository.findOne(id);
        if (coverLetter == null) {
            throw new NotFoundException("Cover letter not found");
        }
        return coverLetter;
    }

    public String findOneHTML(String id) throws NotFoundException, TransformationException, RepositoryException {
        String coverLetter = coverLetterRepository.findOne(id);
        if (coverLetter == null) {
            throw new NotFoundException("Cover letter not found");
        }
        return xslTransformer.generateHTML(coverLetter, "data/xsl/xsl-t/CoverLetterToHTML.xsl");
    }

    public String create(String scientificPaperId, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, coverLetterSchemaPath);

        String id = idGenerator.createID();
        String fullUrl = TARGET_NAMESPACE + "/cover-letters/" + id;
                document.getDocumentElement().setAttribute("id", fullUrl);

        NodeList paragraphs = document.getElementsByTagName("paragraph");
        for (int i = 0; i < paragraphs.getLength(); ++i) {
            idGenerator.generateParagraphID(paragraphs.item(i), fullUrl + "/paragraphs/" + (i + 1));
        }

        NodeList authors = document.getElementsByTagName("author");
        idGenerator.generateUserIDs(authors);

        NodeList editors = document.getElementsByTagName("editor");
        idGenerator.generateUserIDs(editors);

        document.getElementsByTagName("scientific-paper-reference").item(0).setTextContent(scientificPaperId);

        String newXml = documentXMLTransformer.toXMLString(document);

        return coverLetterRepository.create(id, newXml);
    }
}
