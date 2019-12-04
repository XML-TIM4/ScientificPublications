package xmlteam4.Project.utilities;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import xmlteam4.Project.exceptions.DocumentParsingFailedException;

import javax.xml.XMLConstants;
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

    public Document buildDocument(String xml, String schemaPath) throws SAXException, ParserConfigurationException, IOException, DocumentParsingFailedException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setValidating(true);

        Schema schema = loadSchema(schemaPath);

        documentBuilderFactory.setSchema(schema);

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setErrorHandler(new DOMErrorHandlerImpl());

        Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));

        if (document == null)
            throw new DocumentParsingFailedException("Failed to parse document");

        return document;
    }

    private Schema loadSchema(String schemaPath) throws SAXException {
        File schemaFile = new File(schemaPath);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);

        return schemaFactory.newSchema(schemaFile);
    }
}
