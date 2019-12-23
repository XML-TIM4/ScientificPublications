package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import xmlteam4.Project.exceptions.DocumentParsingFailedException;
import xmlteam4.Project.repositories.CoverLetterRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.transformer.XSLTransfomer;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Service
public class CoverLetterService {

    @Autowired
    private CoverLetterRepository coverLetterRepository;

    @Autowired
    private XSLTransfomer xslTransfomer;

    public String findOne(String id) throws Exception {
        String cover = coverLetterRepository.findOne(id);
        if(cover == null) {
            throw new NotFoundException(String.format("Cover letter with id %s is not found", id));
        }
        String clHTML = xslTransfomer.generateHTML(cover, CoverLetterRepository.coverLetterXSLPath);
        return clHTML;

    }

    public String create(String scientificPaperId, String xml) throws Exception {
        DOMParser domParser = new DOMParser();
        Document document = domParser.buildDocument(xml,CoverLetterRepository.coverLetterSchemaPath);
        String id = document.getDocumentElement().getAttribute("id");

        String cover = coverLetterRepository.create(id,xml);
        return cover;
    }
}
