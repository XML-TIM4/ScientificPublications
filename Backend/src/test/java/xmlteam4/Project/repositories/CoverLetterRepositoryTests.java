package xmlteam4.Project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.w3c.dom.Document;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoverLetterRepositoryTests {
    @Autowired
    CoverLetterRepository coverLetterRepository;

    @Autowired
    IDGenerator idGenerator;
/*
    @Autowired
    DOMParser domParser;

    @Value("${cover-letter-schema-path}")
    private String coverLetterSchemaPath;
*/
    @Test
    void create_ValidCoverLetter_Equals() throws Exception {
        String cl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<cover-letter xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/XMLSchemas/CoverLetter.xsd\">\n" +
                "    <cover-letter-metadata>\n" +
                "        <author>\n" +
                "            <name>\n" +
                "                <first-name>first-name0</first-name>\n" +
                "                <last-name>last-name0</last-name>\n" +
                "            </name>\n" +
                "            <email>admin@admin.com</email>\n" +
                "            <affiliation>\n" +
                "                <university>university0</university>\n" +
                "                <city>city0</city>\n" +
                "                <state>state0</state>\n" +
                "                <country>country0</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "        <date>2006-05-04</date>\n" +
                "        <scientific-paper-reference>scientific-paper-reference0</scientific-paper-reference>\n" +
                "    </cover-letter-metadata>\n" +
                "    <content>\n" +
                "        <paragraph>\n" +
                "            <figure>\n" +
                "                <image>ZGVmYXVsdA==</image>\n" +
                "                <image>ZGVmYXVsdA==</image>\n" +
                "            </figure>\n" +
                "            <table>\n" +
                "                <header>\n" +
                "                </header>\n" +
                "                <body>\n" +
                "                </body>\n" +
                "            </table>\n" +
                "        </paragraph>\n" +
                "        <paragraph>\n" +
                "            <quote>\n" +
                "                <quote-text>quote-text0</quote-text>\n" +
                "                <publication>\n" +
                "                    <title>title0</title>\n" +
                "                    <publisher>publisher0</publisher>\n" +
                "                    <place>place0</place>\n" +
                "                    <url>http://www.findtheinvisiblecow.com</url>\n" +
                "                </publication>\n" +
                "            </quote>\n" +
                "            <table>\n" +
                "                <header>\n" +
                "                </header>\n" +
                "                <body>\n" +
                "                </body>\n" +
                "            </table>\n" +
                "        </paragraph>\n" +
                "        <signature>ZGVmYXVsdA==</signature>\n" +
                "    </content>\n" +
                "</cover-letter>\n";

        String id = idGenerator.createID();
/*
        Document document = domParser.buildDocument(cl, coverLetterSchemaPath);

        document.getElementsByTagName("scientific-paper-reference").item(0).setTextContent("jojojo");

        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        System.out.println(writer.toString());
*/
        assertEquals(id, coverLetterRepository.create(id, cl));
    }
}
