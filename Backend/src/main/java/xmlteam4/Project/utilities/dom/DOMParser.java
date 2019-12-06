package xmlteam4.Project.utilities.dom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import xmlteam4.Project.exceptions.DocumentParsingFailedException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

@Component
public class DOMParser {
    @Autowired
    private DocumentBuilderFactory documentBuilderFactory;

    @Autowired
    private SchemaFactory schemaFactory;

    public Document buildDocument(String xml, String schemaPath) throws SAXException, ParserConfigurationException, IOException, DocumentParsingFailedException {
        documentBuilderFactory.setSchema(loadSchema(schemaPath));

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));

        if (document == null)
            throw new DocumentParsingFailedException("Failed to parse document");

        return document;
    }

    private Schema loadSchema(String schemaPath) throws SAXException {
        return schemaFactory.newSchema(new File(schemaPath));
    }
}
