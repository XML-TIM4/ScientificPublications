package xmlteam4.Project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static xmlteam4.Project.utilities.exist.XUpdateTemplate.TARGET_NAMESPACE;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoverLetterServiceTests {

    @Autowired
    private CoverLetterService coverLetterService;

    @Autowired
    IDGenerator idGenerator;

    @Autowired
    XSLTransformer xslTransformer;

    @Test
    void create_validCoverLetter_Equals() throws Exception{
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

        String scientificPaperId = idGenerator.createID();

        String id = TARGET_NAMESPACE + "/cover-letters/" + coverLetterService.create(scientificPaperId, cl);

        String found = coverLetterService.getCoverLetterXML(id);

        System.out.println(found);
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }

    @Test
    void create_validCoverLetter_Equals2() throws Exception{
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

        String scientificPaperId = idGenerator.createID();

        String id = TARGET_NAMESPACE + "/cover-letters/" + coverLetterService.create(scientificPaperId, cl);

        String found = coverLetterService.getCoverLetterHTML(id);

        System.out.println(found);
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }

    @Test
    void create_validCoverLetterPDF_Equals() throws Exception{
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

        String scientificPaperId = idGenerator.createID();

        String id = TARGET_NAMESPACE + "/cover-letters/" + coverLetterService.create(scientificPaperId, cl);

        String found = coverLetterService.getCoverLetterXML(id);


        ByteArrayOutputStream stream = xslTransformer.generatePDF(found, "data/xsl/xsl-fo/CoverLetterToPDF.xsl");
        try(OutputStream outputStream = new FileOutputStream("coverLetter.pdf")) {
            stream.writeTo(outputStream);
        }
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }


}
