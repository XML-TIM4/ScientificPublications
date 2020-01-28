package xmlteam4.Project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.w3c.dom.Document;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformers.documentxmltransformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationRepositoryTests {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    XSLTransformer xslTransformer;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    private String notificationSchemaPath = "./data/schemas/Notification.xsd";

    @Test
    void create_ValidNotification_Equals() throws Exception {
        String notification = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<notification xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/XMLSchemas/Notification.xsd\">\n" +
                "    <notification-metadata>\n" +
                "        <recipient>\n" +
                "            <name>\n" +
                "                <first-name>first-name0</first-name>\n" +
                "                <middle-name>middle-name0</middle-name>\n" +
                "                <middle-name>middle-name1</middle-name>\n" +
                "                <last-name>last-name0</last-name>\n" +
                "            </name>\n" +
                "            <email> @ .a</email>\n" +
                "            <affiliation>\n" +
                "                <university>university0</university>\n" +
                "                <city>city0</city>\n" +
                "                <state>state0</state>\n" +
                "                <country>country0</country>\n" +
                "            </affiliation>\n" +
                "        </recipient>\n" +
                "        <date>2006-05-04</date>\n" +
                "        <scientific-paper-id>1AHYLIaeOD92f6Ic9TqnXo</scientific-paper-id>\n" +
                "    </notification-metadata>\n" +
                "    <content>\n" +
                "        <quote>\n" +
                "            <quote-text>quote-text0</quote-text>\n" +
                "            <publication>\n" +
                "                <title>title0</title>\n" +
                "                <publisher>publisher0</publisher>\n" +
                "                <place>place0</place>\n" +
                "            </publication>\n" +
                "        </quote>\n" +
                "        <list>\n" +
                "        </list>\n" +
                "    </content>\n" +
                "</notification>\n";

        String id = IDGenerator.createID();

        Document document = domParser.buildDocument(notification, notificationSchemaPath);
        document.getDocumentElement().setAttribute("id", id);

        notification = documentXMLTransformer.toXMLString(document);

        String ret = notificationRepository.create(id, notification);
        String found = notificationRepository.findOne(ret);

        String html = xslTransformer.generateHTML(found, "data/xsl/xsl-t/NotificationToHTML.xsl");

        System.out.println(html);
        assertEquals(id, ret);
    }
}
