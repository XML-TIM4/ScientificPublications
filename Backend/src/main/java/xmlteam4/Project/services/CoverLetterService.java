package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import xmlteam4.Project.exceptions.DocumentParsingFailedException;
import xmlteam4.Project.repositories.CoverLetterRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformer.XSLTransfomer;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Service
public class CoverLetterService {

    @Autowired
    private CoverLetterRepository coverLetterRepository;

    //@Autowired
    //private XSLTransfomer xslTransfomer;

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

    public String create(String scientificPaperId, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, coverLetterSchemaPath);

        String id = IDGenerator.createID();
        document.getDocumentElement().setAttribute("id", id);

        document.getElementsByTagName("scientific-paper-reference").item(0).setTextContent(scientificPaperId);

        String newXml = documentXMLTransformer.toXMLString(document);

        return coverLetterRepository.create(id, newXml);
    }
}
