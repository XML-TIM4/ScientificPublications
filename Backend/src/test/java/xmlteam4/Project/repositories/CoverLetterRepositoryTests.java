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
/*
    @Autowired
    DOMParser domParser;

    @Value("${cover-letter-schema-path}")
    private String coverLetterSchemaPath;
*/
    @Test
    void create_ValidCoverLetter_Equals() throws Exception {
        String cl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                "<CoverLetter xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\r\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/XMLSchemas/CoverLetter.xsd\">\r\n" +
                "    <cover-letter-metadata>\r\n" +
                "        <author>\r\n" +
                "            <name>\r\n" +
                "                <first-name>first-name0</first-name>\r\n" +
                "                <last-name>last-name0</last-name>\r\n" +
                "            </name>\r\n" +
                "            <email> @ .a</email>\r\n" +
                "            <affiliation>\r\n" +
                "                <university>university0</university>\r\n" +
                "                <city>city0</city>\r\n" +
                "                <state>state0</state>\r\n" +
                "                <country>country0</country>\r\n" +
                "            </affiliation>\r\n" +
                "        </author>\r\n" +
                "        <editor>\r\n" +
                "            <name>\r\n" +
                "                <first-name>first-name1</first-name>\r\n" +
                "                <last-name>last-name1</last-name>\r\n" +
                "            </name>\r\n" +
                "            <email> @ .a</email>\r\n" +
                "            <affiliation>\r\n" +
                "                <university>university1</university>\r\n" +
                "                <city>city1</city>\r\n" +
                "                <state>state1</state>\r\n" +
                "                <country>country1</country>\r\n" +
                "            </affiliation>\r\n" +
                "        </editor>\r\n" +
                "        <date>2006-05-04</date>\r\n" +
                "        <scientific-paper-reference>scientific-paper-reference0</scientific-paper-reference>\r\n" +
                "    </cover-letter-metadata>\r\n" +
                "    <content>\r\n" +
                "        <paragraph>\r\n" +
                "            <figure>\r\n" +
                "                <image>ZGVmYXVsdA==</image>\r\n" +
                "                <image>ZGVmYXVsdA==</image>\r\n" +
                "            </figure>\r\n" +
                "            <table>\r\n" +
                "                <header>\r\n" +
                "                </header>\r\n" +
                "                <body>\r\n" +
                "                </body>\r\n" +
                "            </table>\r\n" +
                "        </paragraph>\r\n" +
                "        <paragraph>\r\n" +
                "            <quote>\r\n" +
                "                <quote-text>quote-text0</quote-text>\r\n" +
                "                <publication>\r\n" +
                "                    <title>title0</title>\r\n" +
                "                    <publisher>publisher0</publisher>\r\n" +
                "                    <place>place0</place>\r\n" +
                "                </publication>\r\n" +
                "            </quote>\r\n" +
                "            <table>\r\n" +
                "                <header>\r\n" +
                "                </header>\r\n" +
                "                <body>\r\n" +
                "                </body>\r\n" +
                "            </table>\r\n" +
                "        </paragraph>\r\n" +
                "        <signature>ZGVmYXVsdA==</signature>\r\n" +
                "    </content>\r\n" +
                "</CoverLetter>\r\n" +
                "";

        String id = IDGenerator.createID();
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
