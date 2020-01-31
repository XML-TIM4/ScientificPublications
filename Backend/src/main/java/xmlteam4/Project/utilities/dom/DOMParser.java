package xmlteam4.Project.utilities.dom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import xmlteam4.Project.exceptions.DocumentParsingFailedException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.StringReader;

@Component
public class DOMParser {
    @Autowired
    private DocumentBuilderFactory documentBuilderFactory;

    @Autowired
    private SchemaFactory schemaFactory;

    public Document buildDocument(String xml, String schemaPath) throws DocumentParsingFailedException {
        try {
            documentBuilderFactory.setSchema(loadSchema(schemaPath));

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            return documentBuilder.parse(new InputSource(new StringReader(xml)));
        } catch (Exception e) {
            throw new DocumentParsingFailedException("Failed to parse document");
        }
    }

    public Document buildDocumentFromFile(String filePath, String schemaPath) throws DocumentParsingFailedException {
        try {
            documentBuilderFactory.setSchema(loadSchema(schemaPath));

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            File file = new File(filePath);

            return documentBuilder.parse(file);
        } catch (Exception e) {
            throw new DocumentParsingFailedException("Failed to parse document");
        }
    }

    private Schema loadSchema(String schemaPath) throws SAXException {
        return schemaFactory.newSchema(new File(schemaPath));
    }
}
