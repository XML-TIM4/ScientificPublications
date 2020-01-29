package xmlteam4.Project.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import java.io.*;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;
import static xmlteam4.Project.utilities.exist.XUpdateTemplate.TARGET_NAMESPACE;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoverLetterServiceTests {

    @Autowired
    private CoverLetterService coverLetterService;

    @Autowired
    private ScientificPaperService scientificPaperService;

    @Autowired
    IDGenerator idGenerator;

    @Autowired
    XSLTransformer xslTransformer;

    static String cl;
    static String sp;

    @BeforeAll
    static void setup() {
        cl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
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

        sp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<scientific-paper xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/data/schemas/ScientificPaper.xsd\" language=\"en\" id=\"\">\n" +
                "    <metadata>\n" +
                "        <received>2019-06-14Z</received>\n" +
                "        <revised>2019-07-10Z</revised>\n" +
                "        <accepted>2019-08-05Z</accepted>\n" +
                "        <version>1.0</version>\n" +
                "        <status>uploaded</status>\n" +
                "        <category>research-paper</category>\n" +
                "        <keywords>\n" +
                "            <keyword>baja</keyword>\n" +
                "            <keyword>moj</keyword>\n" +
                "        </keywords>\n" +
                "    </metadata>\n" +
                "    <title>Jako Dobar Rad Bajo Moj</title>\n" +
                "    <authors>\n" +
                "        <author id=\"\">\n" +
                "            <name>\n" +
                "                <first-name>Bajcina</first-name>\n" +
                "                <middle-name>Bajo</middle-name>\n" +
                "                <last-name>Bajic</last-name>\n" +
                "            </name>\n" +
                "            <email>admin@admin.com</email>\n" +
                "            <affiliation>\n" +
                "                <university>Univerzitet Bajomira Bajica</university>\n" +
                "                <city>Bajograd</city>\n" +
                "                <state>Bajodina</state>\n" +
                "                <country>Srbija</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "        <author id=\"\">\n" +
                "            <name>\n" +
                "                <first-name>Lep</first-name>\n" +
                "                <middle-name>Zgodan</middle-name>\n" +
                "                <middle-name>Neophodan</middle-name>\n" +
                "                <last-name>Covek</last-name>\n" +
                "            </name>\n" +
                "            <email>admin@admin.com</email>\n" +
                "            <affiliation>\n" +
                "                <university>Univerzitet lepote</university>\n" +
                "                <city>Zgodnograd</city>\n" +
                "                <state>Neophod</state>\n" +
                "                <country>Covecija</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "    </authors>\n" +
                "    <abstract id=\"\">\n" +
                "        <abstract-item title=\"Purpose\">Bla bla bla</abstract-item>\n" +
                "        <abstract-item title=\"Findings\">Blla</abstract-item>\n" +
                "        <abstract-item title=\"Originality\">Blla</abstract-item>\n" +
                "        <abstract-item title=\"Methodology\">Blla</abstract-item>\n" +
                "        <abstract-item title=\"Social Implications\">Bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla </abstract-item>\n" +
                "        <abstract-item title=\"Implications\">Hvala na paznji</abstract-item>\n" +
                "    </abstract>\n" +
                "    <content>\n" +
                "        <section id=\"/oi\">\n" +
                "            <title>Titl1</title>\n" +
                "            <paragraph id=\" #~\">\n" +
                "                <figure type=\"equation\" id=\"\">\n" +
                "                    <image>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</image>\n" +
                "                    <image>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</image>\n" +
                "                </figure>\n" +
                "                <quote>\n" +
                "                    <quote-text>T1KADIxas5jp0cC395gtLJR2izQQ8</quote-text>\n" +
                "                    <publication>\n" +
                "                        <title>yZ7DDR</title>\n" +
                "                        <publisher>j62Ajr2pK46f0ZKXw</publisher>\n" +
                "                        <place>Q2uQwWl_O9Rv</place>\n" +
                "                        <url>http://www.findtheinvisiblecow.com</url>\n" +
                "                    </publication>\n" +
                "                </quote>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"\">\n" +
                "                <table title=\"Pesmica\" id=\"Q+://=\">\n" +
                "                    <header>\n" +
                "                        <row>\n" +
                "                            <column>ahem</column>\n" +
                "                        </row>\n" +
                "                    </header>\n" +
                "                    <body>\n" +
                "                        <row>\n" +
                "                            <column>Dal si ikada</column>\n" +
                "                        </row>\n" +
                "                        <row>\n" +
                "                            <column>Mene voljela</column>\n" +
                "                        </row>\n" +
                "                        <row>\n" +
                "                            <column>Kao tebe ja</column>\n" +
                "                        </row>\n" +
                "                    </body>\n" +
                "                </table>\n" +
                "                <quote>\n" +
                "                    <quote-text>ZlN12f0as9TQI1rqgOvZA</quote-text>\n" +
                "                    <publication>\n" +
                "                        <title>NhL4K8a</title>\n" +
                "                        <publisher>P7AKE4rofSGKjbS.2LGPsrwQSh.E-150laxyri_7dIiTCBt3c4mT6dMDCLYYv6vE</publisher>\n" +
                "                        <place>Vdij5WAXb82_.0TOKHTOQXxde8VHsJyccT</place>\n" +
                "                        <url>http://www.findtheinvisiblecow.com</url>\n" +
                "                    </publication>\n" +
                "                </quote>\n" +
                "            </paragraph>\n" +
                "        </section>\n" +
                "        <section id=\"\">\n" +
                "            <title>Titl2</title>\n" +
                "            <paragraph id=\"\">\n" +
                "                <table title=\"Pesmica v2\" id=\"\">\n" +
                "                    <header>\n" +
                "                        <row>\n" +
                "                            <column>ahem ahem</column>\n" +
                "                        </row>\n" +
                "                    </header>\n" +
                "                    <body>\n" +
                "                        <row>\n" +
                "                            <column>MENE VOLJELA</column>\n" +
                "                        </row>\n" +
                "                        <row>\n" +
                "                            <column>KAO TEBE JA</column>\n" +
                "                        </row>\n" +
                "                        <row>\n" +
                "                            <column>DAL SI IKADA</column>\n" +
                "                        </row>\n" +
                "                    </body>\n" +
                "                </table>\n" +
                "                <decorator>\n" +
                "                    <italic>t\n" +
                "                        <underline>h\n" +
                "                            <bold>i\n" +
                "                            </bold>\n" +
                "                            <bold>s\n" +
                "                            </bold>\n" +
                "                        </underline>i\n" +
                "                        <bold>s\n" +
                "                            <underline>g\n" +
                "                            </underline>\n" +
                "                            <underline>o\n" +
                "                            </underline>\n" +
                "                        </bold>o\n" +
                "                    </italic>d\n" +
                "                    <italic>s\n" +
                "                        <underline>t\n" +
                "                            <bold>u\n" +
                "                            </bold>\n" +
                "                            <bold>\n" +
                "                            </bold>f\n" +
                "                        </underline>\n" +
                "                        <bold>f\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                            <underline>z\n" +
                "                            </underline>\n" +
                "                        </bold>\n" +
                "                    </italic>\n" +
                "                </decorator>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"P:/\\!q. #~\">\n" +
                "                <figure type=\"figure\" id=\"\">\n" +
                "                    <image>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</image>\n" +
                "                    <image>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</image>\n" +
                "                </figure>\n" +
                "                <figure type=\"equation\" id=\"\">\n" +
                "                    <image>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</image>\n" +
                "                    <image>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</image>\n" +
                "                </figure>\n" +
                "            </paragraph>\n" +
                "        </section>\n" +
                "    </content>\n" +
                "    <references>\n" +
                "        <reference>\n" +
                "            <authors>\n" +
                "                <name>\n" +
                "                    <first-name>Neki</first-name>\n" +
                "                    <middle-name>Bas</middle-name>\n" +
                "                    <middle-name>Baja</middle-name>\n" +
                "                    <last-name>Lik</last-name>\n" +
                "                </name>\n" +
                "                <name>\n" +
                "                    <first-name>Neka</first-name>\n" +
                "                    <middle-name>Mnogo</middle-name>\n" +
                "                    <middle-name>Velika</middle-name>\n" +
                "                    <last-name>Bajcina</last-name>\n" +
                "                </name>\n" +
                "            </authors>\n" +
                "            <publication>\n" +
                "                <title>Kako biti Baja</title>\n" +
                "                <year>2019</year>\n" +
                "                <publisher>Ujedinjene Baje Srbije</publisher>\n" +
                "                <place>Bajograd</place>\n" +
                "                <url>http://www.findtheinvisiblecow.com</url>\n" +
                "            </publication>\n" +
                "        </reference>\n" +
                "        <reference>\n" +
                "            <authors>\n" +
                "                <name>\n" +
                "                    <first-name>Mirko</first-name>\n" +
                "                    <middle-name>M</middle-name>\n" +
                "                    <last-name>Mirkic</last-name>\n" +
                "                </name>\n" +
                "                <name>\n" +
                "                    <first-name>Petar</first-name>\n" +
                "                    <last-name>Petrovic</last-name>\n" +
                "                </name>\n" +
                "                <name>\n" +
                "                    <first-name>Nikola</first-name>\n" +
                "                    <last-name>Nikol.. a da</last-name>\n" +
                "                </name>\n" +
                "            </authors>\n" +
                "            <publication>\n" +
                "                <title>Bas bruka rad</title>\n" +
                "                <year>2015</year>\n" +
                "                <publisher>Fakultet Nikolica</publisher>\n" +
                "                <place>Mirijevo</place>\n" +
                "                <url>http://www.findtheinvisiblecow.com</url>\n" +
                "            </publication>\n" +
                "        </reference>\n" +
                "    </references>\n" +
                "</scientific-paper>\n";
    }

    @Test
    void create_validCoverLetter_Equals() throws Exception {

        String scientificPaperId = scientificPaperService.createScientificPaper(sp);
        String id = coverLetterService.create(scientificPaperId, cl);

        String found = coverLetterService.getCoverLetterXML(id);

        System.out.println(found);
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }

    @Test
    void create_validCoverLetter_Equals2() throws Exception{

        String scientificPaperId = scientificPaperService.createScientificPaper(sp);
        String id = coverLetterService.create(scientificPaperId, cl);

        String found = coverLetterService.getCoverLetterHTML(id);

        System.out.println(found);
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }

    @Test
    void create_validCoverLetterPDF_Equals() throws Exception{

        String scientificPaperId = scientificPaperService.createScientificPaper(sp);
        String id = coverLetterService.create(scientificPaperId, cl);

        String found = coverLetterService.getCoverLetterXML(id);

        InputStream jo = coverLetterService.getCoverLetterPDF(id).getInputStream();
        File file = new File("coverLetter.pdf");
        java.nio.file.Files.copy(jo, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }


}
