package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import xmlteam4.Project.repositories.CoverLetterRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformer.XSLTransformer;

@Service
public class CoverLetterService {

    @Autowired
    private CoverLetterRepository coverLetterRepository;

    @Autowired
    private XSLTransformer xslTransformer;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    @Value("${cover-letter-schema-path}")
    private String coverLetterSchemaPath;

    public String findOne(String id) throws Exception {
        String coverLetter = coverLetterRepository.findOne(id);
        if(coverLetter == null) {
            throw new NotFoundException(String.format("Cover letter with id %s is not found", id));
        }
        // transformation to be added later. code saved for future reference
        //String clHTML = xslTransfomer.generateHTML(coverLetter, coverLetterXSLPath);
        return coverLetter;
    }

    public String findOneHTML(String id) throws Exception {
        String coverLetter = coverLetterRepository.findOne(id);
        if(coverLetter == null) {
            throw new NotFoundException(String.format("Cover letter with id %s is not found", id));
        }
        // transformation to be added later. code saved for future reference
        String clHTML = xslTransformer.generateHTML(coverLetter, "data/xsl/xsl-t/CoverLetter.xsl");
        return clHTML;
    }

    public String create(String scientificPaperId, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, coverLetterSchemaPath);

        String id = IDGenerator.createID();
        document.getDocumentElement().setAttribute("id", id);

        NodeList paragraphs = document.getElementsByTagName("paragraph");
        for (int i = 0; i < paragraphs.getLength(); ++i) {
            IDGenerator.generateParagraphID(paragraphs.item(i), id + "/paragraphs/" + (i+1));
        }

        NodeList authors = document.getElementsByTagName("author");
        for (int i = 0; i < authors.getLength(); ++i) {
            IDGenerator.generateChildlessElementID(authors.item(i), id + "/authors/" + (i+1), "author");
        }

        NodeList editors = document.getElementsByTagName("editor");
        for (int i = 0; i < editors.getLength(); ++i) {
            IDGenerator.generateChildlessElementID(editors.item(i), id + "/editors/" + (i+1), "editor");
        }

        document.getElementsByTagName("scientific-paper-reference").item(0).setTextContent(scientificPaperId);

        String newXml = documentXMLTransformer.toXMLString(document);

        return coverLetterRepository.create(id, newXml);
    }
}
