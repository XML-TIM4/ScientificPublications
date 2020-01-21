package xmlteam4.Project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xmlteam4.Project.utilities.transformer.XSLTransformer;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ScientificPaperServiceTests {

    @Autowired
    ScientificPaperService scientificPaperService;

    @Autowired
    XSLTransformer xslTransformer;

    @Test
    void testXSLT() throws Exception {
        String scientificPaper = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<scientific-paper xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/Backend/data/schemas/ScientificPaper.xsd\" language=\"en\" id=\"D1Fb5qToobRahOi4r\">\n" +
                "    <metadata>\n" +
                "        <received>2021-08-17</received>\n" +
                "        <revised>2020-01-20</revised>\n" +
                "        <accepted>2020-11-17</accepted>\n" +
                "        <version>1.0</version>\n" +
                "        <status>rejected</status>\n" +
                "        <category>research-paper</category>\n" +
                "        <keywords>\n" +
                "            <keyword>SQZndS</keyword>\n" +
                "            <keyword>E43OYaJDLCfZH2jXjNwcdkKiRt-C</keyword>\n" +
                "        </keywords>\n" +
                "    </metadata>\n" +
                "    <title>nHc2-LIZB4cQ0w8BHpRL</title>\n" +
                "    <authors>\n" +
                "        <author id=\"GBb7fJh6QUKiGS5Gvx\">\n" +
                "            <name>\n" +
                "                <first-name>NmAS3EmL5-RkqaMIDeJeSaV6p-vVOP05NEPuph_BsD_iHhLlc</first-name>\n" +
                "                <middle-name>oFfw6zCM2lZ.GuCwkSd2WJ6OqCP</middle-name>\n" +
                "                <middle-name>g.40clp9u1Y5IXbMtYI2LIF4ivICRWtoQ_TfK.9NCuy_RWN</middle-name>\n" +
                "                <last-name>sy-FlI_3kGqqbDertMs30lfDz8BK4SsJN1qh</last-name>\n" +
                "            </name>\n" +
                "            <email>Ú@~V +.ara</email>\n" +
                "            <affiliation>\n" +
                "                <university>VoHFvE2_OlIr0ALJL9e49YvCfQmA2DEVuH4fQrnQOd3JeX</university>\n" +
                "                <city>Xy8J.DtF4.nBM8tAkGNB</city>\n" +
                "                <state>A3YFLsN6UxoI8KpSRYiyB2</state>\n" +
                "                <country>yxv0LI1I6-yDFNAWoyNyzQhxIqtx</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "        <author id=\"lVU2sou5t3c271hfxo02D0nhg7H9\">\n" +
                "            <name>\n" +
                "                <first-name>WDFtRlr.AIQ203jLw1deA</first-name>\n" +
                "                <middle-name>yhe37.9cWiNf</middle-name>\n" +
                "                <middle-name>eQet1Q4GkcnQWi4E8bFMgRlZplA3dyae30</middle-name>\n" +
                "                <last-name>N7LFEd3nqhg4YIdR2n6J</last-name>\n" +
                "            </name>\n" +
                "            <email>í0;@g.ofn</email>\n" +
                "            <affiliation>\n" +
                "                <university>eAsKkexx-R9cBjzFpLl.7MBVK_wRIZP5ewxfU3QEQR0</university>\n" +
                "                <city>MfpyQLQ</city>\n" +
                "                <state>UkrRXZqdlh9LtRWvCCuBVZqsYZDIhgx0uwXmJrSJ52Xvl_</state>\n" +
                "                <country>Mf3EMT8NZPkyQBRjgAUPWOCQMzhs57IUEuc</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "    </authors>\n" +
                "    <abstract id=\"T0jAFTekYhnMXNO3_Tt_.hz5\">\n" +
                "        <abstract-item title=\"Findings\">ai1</abstract-item>\n" +
                "        <abstract-item title=\"Findings\">ai2</abstract-item>\n" +
                "        <abstract-item title=\"Purpose\">ai3</abstract-item>\n" +
                "        <abstract-item title=\"Originality\">ai4</abstract-item>\n" +
                "    </abstract>\n" +
                "    <content>\n" +
                "        <section id=\"wa6W.a\">\n" +
                "            <title>GMTAuAP6QRIon6bc4JhPCSg</title>\n" +
                "            <paragraph id=\"d7_an5yQfXkJdi2O7Af-\">\n" +
                "                <list ordered=\"false\">\n" +
                "                    <list-item>wvRuwm</list-item>\n" +
                "                    <list-item>BaaJMjoD35</list-item>\n" +
                "                </list>\n" +
                "                <list ordered=\"false\">\n" +
                "                    <list-item>z70CedbA55IAqB5sh0nWPCrMg0KY1QcL.A</list-item>\n" +
                "                    <list-item>g0NmGkr7JWcTE</list-item>\n" +
                "                </list>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"V\">\n" +
                "                <decorator>\n" +
                "                    <bold>\n" +
                "                        <underline>\n" +
                "                            <italic>\n" +
                "                            </italic>\n" +
                "                            <italic>\n" +
                "                            </italic>\n" +
                "                        </underline>\n" +
                "                        <italic>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                        </italic>\n" +
                "                    </bold>\n" +
                "                    <bold>\n" +
                "                        <underline>\n" +
                "                            <italic>\n" +
                "                            </italic>\n" +
                "                            <italic>\n" +
                "                            </italic>\n" +
                "                        </underline>\n" +
                "                        <italic>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                        </italic>\n" +
                "                    </bold>\n" +
                "                </decorator>\n" +
                "                <table title=\"Tst-PhHf4G69CzWXIZiH-6SZ_mb6STWkv45AWzXrv\" id=\"jO99nL.sSLALjvQtb\">\n" +
                "                    <header>\n" +
                "                    </header>\n" +
                "                    <body>\n" +
                "                    </body>\n" +
                "                </table>\n" +
                "            </paragraph>\n" +
                "        </section>\n" +
                "        <section id=\"GRJupe6Ztle6ID.5fOWIaoL\">\n" +
                "            <title>TZ0tMkgcqtCJkFbPdqWDQmgweIS6OKEr9l3r0er9X</title>\n" +
                "            <paragraph id=\"FGhFFIqPutOjyTs1iBJHmA2teRQ\">\n" +
                "                <table title=\"WYn902CqAu4xfM-.0p9Y\" id=\"N5I9SD7lD\">\n" +
                "                    <header>\n" +
                "                    </header>\n" +
                "                    <body>\n" +
                "                    </body>\n" +
                "                </table>\n" +
                "                <quote>\n" +
                "                    <quote-text>l0PSj3G3AJZzI4m</quote-text>\n" +
                "                    <publication>\n" +
                "                        <title>CbbwsQomXGs_pyS_2H4mCTtYwUYi7nzOJ7IDwz9R0UhVFbJ7PbFj85qBIq4AuXAt</title>\n" +
                "                        <publisher>t9W.4RSKCYTFRxj1B_WhFc.wEjF3qtEsPlrP8Nm0</publisher>\n" +
                "                        <place>btQs81mwMQx-.NAVqZeziId6b8q</place>\n" +
                "                    </publication>\n" +
                "                </quote>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"Lf2wm__VMC\">\n" +
                "                <list ordered=\"false\">\n" +
                "                    <list-item>INCQCV7Fewv</list-item>\n" +
                "                    <list-item>y6HN3LEHzvVzLphaoN2ntGiVaFYgj4y0zHXMEcr</list-item>\n" +
                "                </list>\n" +
                "                <table title=\"RGBkBJp9vAG_aMDXEb-S8esXhzhcio-mG8khhqBBPzXax\" id=\"n_ZS067H5Tp80PFP9tU\">\n" +
                "                    <header>\n" +
                "                    </header>\n" +
                "                    <body>\n" +
                "                    </body>\n" +
                "                </table>\n" +
                "            </paragraph>\n" +
                "        </section>\n" +
                "    </content>\n" +
                "    <references>\n" +
                "        <reference>\n" +
                "            <authors>\n" +
                "                <name>\n" +
                "                    <first-name>agUMSxxtVF1eIg</first-name>\n" +
                "                    <middle-name>T</middle-name>\n" +
                "                    <middle-name>bVgMoD5eVdAIsGtlsEg3-H5o16JtbG-wsjE.EsXc</middle-name>\n" +
                "                    <last-name>UX1QnbHFi1ytpRnoE-LABP2TYkI9oyn</last-name>\n" +
                "                </name>\n" +
                "                <name>\n" +
                "                    <first-name>e1v08KnYl2IcRAS-l69So_IaHzufh_PYRkPofsFLdZwQy7ta</first-name>\n" +
                "                    <middle-name>RAsuf8sJ.ZyrxQ80krfyRM1H2CHH_</middle-name>\n" +
                "                    <middle-name>fUQAO8d.Q5MyYig8k2.</middle-name>\n" +
                "                    <last-name>cHc6PUyc.JADKrnzoSEimBERLFm0.wnsK-Cx8NKXs</last-name>\n" +
                "                </name>\n" +
                "            </authors>\n" +
                "            <publication>\n" +
                "                <title>iRu_xfFhfs2hMEXmj1ILmR5vsJOZY._MNvFN61tF4vXxwvCR4lrFrm.LpTtsNrmaOQ-Yiw</title>\n" +
                "                <year>1694</year>\n" +
                "                <publisher>iJ</publisher>\n" +
                "                <place>ylU834Nbzs_6B_6SFGMxk8wYVUKsH6u</place>\n" +
                "            </publication>\n" +
                "        </reference>\n" +
                "        <reference>\n" +
                "            <authors>\n" +
                "                <name>\n" +
                "                    <first-name>DmmCuiIwiFsKeL</first-name>\n" +
                "                    <middle-name>fSGPdRymA89P5xdCw2Q9p</middle-name>\n" +
                "                    <middle-name>Z2y32CgtZmDDnTkB</middle-name>\n" +
                "                    <last-name>F-pLJXcGS8fQSENU</last-name>\n" +
                "                </name>\n" +
                "                <name>\n" +
                "                    <first-name>mp03</first-name>\n" +
                "                    <middle-name>tGOp28_L6d2ErQhWIgI</middle-name>\n" +
                "                    <middle-name>ZhgOnJuB7JtXc</middle-name>\n" +
                "                    <last-name>ZN</last-name>\n" +
                "                </name>\n" +
                "            </authors>\n" +
                "            <publication>\n" +
                "                <title>CgCHbDCEYnd-jWuzY4pLJjOHX3JokXTP7iEgV.xUBLyskZC_yvO7eZGcGgsbvUKptbcEYBJ19uNwJptJ9xiBHnD9YFZ</title>\n" +
                "                <year>1043</year>\n" +
                "                <publisher>ZL</publisher>\n" +
                "                <place>gIAeB</place>\n" +
                "            </publication>\n" +
                "        </reference>\n" +
                "    </references>\n" +
                "</scientific-paper>\n";

        String result = xslTransformer.generateHTML(scientificPaper, "D:\\ScientificPublications\\Backend\\data\\xsl" +
                "\\xsl-t\\ScientificPaperToRDFa.xsl");

        System.out.println(result);

        assertTrue(true);
    }

    @Test
    void create_validScientificPaper_equals() throws Exception {
        String scientificPaper = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<scientific-paper xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/XMLSchemas/ScientificPaper.xsd\" language=\"en\" id=\"\">\n" +
                "    <metadata>\n" +
                "        <received>2021-06-14Z</received>\n" +
                "        <revised>2020-03-10Z</revised>\n" +
                "        <accepted>2020-08-05Z</accepted>\n" +
                "        <version>1.0</version>\n" +
                "        <status>uploaded</status>\n" +
                "        <category>research-paper</category>\n" +
                "        <keywords>\n" +
                "            <keyword>gBjYErt7SbwGkjQMPiRQ-eno12</keyword>\n" +
                "            <keyword>v</keyword>\n" +
                "        </keywords>\n" +
                "    </metadata>\n" +
                "    <title>gYz5LPRg.fDxCPZvDkm-eYFs2E_8bq3Vs7bW86-y_di4HFr_kZsuq5Bi-xK_I7iet3d3FoTP_7</title>\n" +
                "    <authors>\n" +
                "        <author id=\"\">\n" +
                "            <name>\n" +
                "                <first-name>M.mJ8Lv5aq0irnN</first-name>\n" +
                "                <middle-name>szOpWlVonEfoMVPOof2xmC3jLM7y6Gw9HH7xQix7j</middle-name>\n" +
                "                <middle-name>bFWzi3Ntu_fm</middle-name>\n" +
                "                <last-name>cotQFAjOAr</last-name>\n" +
                "            </name>\n" +
                "            <email>\u008C%$ª@'.r</email>\n" +
                "            <affiliation>\n" +
                "                <university>RC8sI0Cxeb3QDv8W8_XbAJBk7rSQNGE-jd.agH6</university>\n" +
                "                <city>wA1qOUBSyN3HVPXOTNfue0</city>\n" +
                "                <state>oRqL7SdkVvCIz</state>\n" +
                "                <country>e8WXZAyJ9EdGdfiC6.TJ0-ehZZFHeXIN1O4UpGHnh</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "        <author id=\"\">\n" +
                "            <name>\n" +
                "                <first-name>lVnDrjmD</first-name>\n" +
                "                <middle-name>sMbuT-zCtFWlSfHUqWa</middle-name>\n" +
                "                <middle-name>SckRDtWrxowu_0CDFY</middle-name>\n" +
                "                <last-name>b1fui-V.3LFgX057iSNzZ-CFt.yJH_P4WPwgqq2qQqV</last-name>\n" +
                "            </name>\n" +
                "            <email>5@K.h</email>\n" +
                "            <affiliation>\n" +
                "                <university>R9b-jMjLmU_NeKYAJ-zFi3FBdMOJC3</university>\n" +
                "                <city>osLL</city>\n" +
                "                <state>uZTuR-Xx1</state>\n" +
                "                <country>UHQTy7mCkrhtR0WpToWiu6rKHKN6</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "    </authors>\n" +
                "    <abstract id=\"\">\n" +
                "        <abstract-item title=\"Purpose\">CzD33J1hPViTWPAqYW82p-mBf</abstract-item>\n" +
                "        <abstract-item title=\"Findings\">DaNoaYAMSz7rH</abstract-item>\n" +
                "        <abstract-item title=\"Social Implications\">y</abstract-item>\n" +
                "        <abstract-item title=\"Implications\">ELyou3bBqFqWnzzm</abstract-item>\n" +
                "    </abstract>\n" +
                "    <content>\n" +
                "        <section id=\"/oi\">\n" +
                "            <title>vCdQ3R2EkpFISjVd1</title>\n" +
                "            <paragraph id=\" #~\">\n" +
                "                <figure type=\"equation\" id=\"\">\n" +
                "                    <image>6985777971898673857571808767746668867878806778797183888389888981857587838588708969737480876985668870866987907586</image>\n" +
                "                    <image>6687816581807875</image>\n" +
                "                </figure>\n" +
                "                <quote>\n" +
                "                    <quote-text>T1KADIxas5jp0cC395gtLJR2izQQ8</quote-text>\n" +
                "                    <publication>\n" +
                "                        <title>yZ7DDR</title>\n" +
                "                        <publisher>j62Ajr2pK46f0ZKXw</publisher>\n" +
                "                        <place>Q2uQwWl_O9Rv</place>\n" +
                "                    </publication>\n" +
                "                </quote>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"\">\n" +
                "                <table title=\"m44MpL\" id=\"Q+://=\">\n" +
                "                    <header>\n" +
                "                    </header>\n" +
                "                    <body>\n" +
                "                    </body>\n" +
                "                </table>\n" +
                "                <quote>\n" +
                "                    <quote-text>ZlN12f0as9TQI1rqgOvZA</quote-text>\n" +
                "                    <publication>\n" +
                "                        <title>NhL4K8a</title>\n" +
                "                        <publisher>P7AKE4rofSGKjbS.2LGPsrwQSh.E-150laxyri_7dIiTCBt3c4mT6dMDCLYYv6vE</publisher>\n" +
                "                        <place>Vdij5WAXb82_.0TOKHTOQXxde8VHsJyccT</place>\n" +
                "                    </publication>\n" +
                "                </quote>\n" +
                "            </paragraph>\n" +
                "        </section>\n" +
                "        <section id=\"\">\n" +
                "            <title>iVjS</title>\n" +
                "            <paragraph id=\"\">\n" +
                "                <table title=\"S\" id=\"\">\n" +
                "                    <header>\n" +
                "                    </header>\n" +
                "                    <body>\n" +
                "                    </body>\n" +
                "                </table>\n" +
                "                <decorator>\n" +
                "                    <italic>\n" +
                "                        <underline>\n" +
                "                            <bold>\n" +
                "                            </bold>\n" +
                "                            <bold>\n" +
                "                            </bold>\n" +
                "                        </underline>\n" +
                "                        <bold>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                        </bold>\n" +
                "                    </italic>\n" +
                "                    <italic>\n" +
                "                        <underline>\n" +
                "                            <bold>\n" +
                "                            </bold>\n" +
                "                            <bold>\n" +
                "                            </bold>\n" +
                "                        </underline>\n" +
                "                        <bold>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                        </bold>\n" +
                "                    </italic>\n" +
                "                </decorator>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"P:/\\!q. #~\">\n" +
                "                <figure type=\"figure\" id=\"\">\n" +
                "                    <image>7088887067698968</image>\n" +
                "                    <image>728186726674707089828986718972868273708078717970737089888390888568867986778767748167658775686582738285777279677169857286</image>\n" +
                "                </figure>\n" +
                "                <figure type=\"equation\" id=\"\">\n" +
                "                    <image>77836766758366718187906780698168</image>\n" +
                "                    <image>ZGVmYXVsdA==</image>\n" +
                "                </figure>\n" +
                "            </paragraph>\n" +
                "        </section>\n" +
                "    </content>\n" +
                "    <references>\n" +
                "        <reference>\n" +
                "            <authors>\n" +
                "                <name>\n" +
                "                    <first-name>RMgd</first-name>\n" +
                "                    <middle-name>OisVa36t5HxURaDudf</middle-name>\n" +
                "                    <middle-name>jUWyIS</middle-name>\n" +
                "                    <last-name>uAhinIkiMr9qh.De-p3CSOcl6OLAqH</last-name>\n" +
                "                </name>\n" +
                "                <name>\n" +
                "                    <first-name>Pb4AF01b</first-name>\n" +
                "                    <middle-name>Hs4PINfY6heUo_66iUrMH086HG.9GjOpYUoRU</middle-name>\n" +
                "                    <middle-name>_TtzY0MN1UZY-FnMk-3xUhaX_qzrGXK9BunljStLooD</middle-name>\n" +
                "                    <last-name>dqZHONv8BJc2rCqofS2x5V6iu-lBwVSKXb9kFgO_1mZ5</last-name>\n" +
                "                </name>\n" +
                "            </authors>\n" +
                "            <publication>\n" +
                "                <title>x3pVcSDs7QjpKc7u591cS9l3vwoaJJwpQvL_SLSQ0MNq9LZXb3B88lcG.YdAEWQjyz0IiaRtXTR94sqAua1xG</title>\n" +
                "                <year>370</year>\n" +
                "                <publisher>eZ</publisher>\n" +
                "                <place>ESgXEgzhhYijceLZB-PVoNbiCci7OkvWo4P24G2q_</place>\n" +
                "            </publication>\n" +
                "        </reference>\n" +
                "        <reference>\n" +
                "            <authors>\n" +
                "                <name>\n" +
                "                    <first-name>FPNtjC.ZmlWmiQXxJTwsR</first-name>\n" +
                "                    <middle-name>j_3</middle-name>\n" +
                "                    <middle-name>Npu67NPio-wNqb2d1Oblms7</middle-name>\n" +
                "                    <last-name>P5-rGxHFWWhsn</last-name>\n" +
                "                </name>\n" +
                "                <name>\n" +
                "                    <first-name>hm2oBm_P</first-name>\n" +
                "                    <middle-name>TfdzwMe5B</middle-name>\n" +
                "                    <middle-name>tqISBd1tj</middle-name>\n" +
                "                    <last-name>xrkSrRX1p3GKWgQDUvsnd_C9z</last-name>\n" +
                "                </name>\n" +
                "            </authors>\n" +
                "            <publication>\n" +
                "                <title>odHc9k8015Lrxn</title>\n" +
                "                <year>327</year>\n" +
                "                <publisher>Jw8ZRAwULwiN1ADe10rHonk7OJSAZrQ0_C4_jj.if2i-ogKH</publisher>\n" +
                "                <place>MidzhbfBlrLqsbiX7UcOl6CjQZxH667en</place>\n" +
                "            </publication>\n" +
                "        </reference>\n" +
                "    </references>\n" +
                "</scientific-paper>\n";

        String id = scientificPaperService.create(scientificPaper);

        String found = scientificPaperService.findOne(id);

        System.out.println(found);

        String wut = scientificPaperService.update(id, found);

        System.out.println("IS THIS IT? " + wut);
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }

    @Test
    void create_validScientificPaperHTML_equals() throws Exception {
        String scientificPaper = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<scientific-paper xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/XMLSchemas/ScientificPaper.xsd\" language=\"en\" id=\"\">\n" +
                "    <metadata>\n" +
                "        <received>2021-06-14Z</received>\n" +
                "        <revised>2020-03-10Z</revised>\n" +
                "        <accepted>2020-08-05Z</accepted>\n" +
                "        <version>1.0</version>\n" +
                "        <status>uploaded</status>\n" +
                "        <category>research-paper</category>\n" +
                "        <keywords>\n" +
                "            <keyword>gBjYErt7SbwGkjQMPiRQ-eno12</keyword>\n" +
                "            <keyword>v</keyword>\n" +
                "        </keywords>\n" +
                "    </metadata>\n" +
                "    <title>gYz5LPRg.fDxCPZvDkm-eYFs2E_8bq3Vs7bW86-y_di4HFr_kZsuq5Bi-xK_I7iet3d3FoTP_7</title>\n" +
                "    <authors>\n" +
                "        <author id=\"\">\n" +
                "            <name>\n" +
                "                <first-name>M.mJ8Lv5aq0irnN</first-name>\n" +
                "                <middle-name>szOpWlVonEfoMVPOof2xmC3jLM7y6Gw9HH7xQix7j</middle-name>\n" +
                "                <middle-name>bFWzi3Ntu_fm</middle-name>\n" +
                "                <last-name>cotQFAjOAr</last-name>\n" +
                "            </name>\n" +
                "            <email>\u008C%$ª@'.r</email>\n" +
                "            <affiliation>\n" +
                "                <university>RC8sI0Cxeb3QDv8W8_XbAJBk7rSQNGE-jd.agH6</university>\n" +
                "                <city>wA1qOUBSyN3HVPXOTNfue0</city>\n" +
                "                <state>oRqL7SdkVvCIz</state>\n" +
                "                <country>e8WXZAyJ9EdGdfiC6.TJ0-ehZZFHeXIN1O4UpGHnh</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "        <author id=\"\">\n" +
                "            <name>\n" +
                "                <first-name>lVnDrjmD</first-name>\n" +
                "                <middle-name>sMbuT-zCtFWlSfHUqWa</middle-name>\n" +
                "                <middle-name>SckRDtWrxowu_0CDFY</middle-name>\n" +
                "                <last-name>b1fui-V.3LFgX057iSNzZ-CFt.yJH_P4WPwgqq2qQqV</last-name>\n" +
                "            </name>\n" +
                "            <email>5@K.h</email>\n" +
                "            <affiliation>\n" +
                "                <university>R9b-jMjLmU_NeKYAJ-zFi3FBdMOJC3</university>\n" +
                "                <city>osLL</city>\n" +
                "                <state>uZTuR-Xx1</state>\n" +
                "                <country>UHQTy7mCkrhtR0WpToWiu6rKHKN6</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "    </authors>\n" +
                "    <abstract id=\"\">\n" +
                "        <abstract-item title=\"Purpose\">CzD33J1hPViTWPAqYW82p-mBf</abstract-item>\n" +
                "        <abstract-item title=\"Findings\">DaNoaYAMSz7rH</abstract-item>\n" +
                "        <abstract-item title=\"Social Implications\">y</abstract-item>\n" +
                "        <abstract-item title=\"Implications\">ELyou3bBqFqWnzzm</abstract-item>\n" +
                "    </abstract>\n" +
                "    <content>\n" +
                "        <section id=\"/oi\">\n" +
                "            <title>vCdQ3R2EkpFISjVd1</title>\n" +
                "            <paragraph id=\" #~\">\n" +
                "                <figure type=\"equation\" id=\"\">\n" +
                "                    <image>6985777971898673857571808767746668867878806778797183888389888981857587838588708969737480876985668870866987907586</image>\n" +
                "                    <image>6687816581807875</image>\n" +
                "                </figure>\n" +
                "                <quote>\n" +
                "                    <quote-text>T1KADIxas5jp0cC395gtLJR2izQQ8</quote-text>\n" +
                "                    <publication>\n" +
                "                        <title>yZ7DDR</title>\n" +
                "                        <publisher>j62Ajr2pK46f0ZKXw</publisher>\n" +
                "                        <place>Q2uQwWl_O9Rv</place>\n" +
                "                    </publication>\n" +
                "                </quote>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"\">\n" +
                "                <table title=\"m44MpL\" id=\"Q+://=\">\n" +
                "                    <header>\n" +
                "                    </header>\n" +
                "                    <body>\n" +
                "                    </body>\n" +
                "                </table>\n" +
                "                <quote>\n" +
                "                    <quote-text>ZlN12f0as9TQI1rqgOvZA</quote-text>\n" +
                "                    <publication>\n" +
                "                        <title>NhL4K8a</title>\n" +
                "                        <publisher>P7AKE4rofSGKjbS.2LGPsrwQSh.E-150laxyri_7dIiTCBt3c4mT6dMDCLYYv6vE</publisher>\n" +
                "                        <place>Vdij5WAXb82_.0TOKHTOQXxde8VHsJyccT</place>\n" +
                "                    </publication>\n" +
                "                </quote>\n" +
                "            </paragraph>\n" +
                "        </section>\n" +
                "        <section id=\"\">\n" +
                "            <title>iVjS</title>\n" +
                "            <paragraph id=\"\">\n" +
                "                <table title=\"S\" id=\"\">\n" +
                "                    <header>\n" +
                "                    </header>\n" +
                "                    <body>\n" +
                "                    </body>\n" +
                "                </table>\n" +
                "                <decorator>\n" +
                "                    <italic>\n" +
                "                        <underline>\n" +
                "                            <bold>\n" +
                "                            </bold>\n" +
                "                            <bold>\n" +
                "                            </bold>\n" +
                "                        </underline>\n" +
                "                        <bold>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                        </bold>\n" +
                "                    </italic>\n" +
                "                    <italic>\n" +
                "                        <underline>\n" +
                "                            <bold>\n" +
                "                            </bold>\n" +
                "                            <bold>\n" +
                "                            </bold>\n" +
                "                        </underline>\n" +
                "                        <bold>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                            <underline>\n" +
                "                            </underline>\n" +
                "                        </bold>\n" +
                "                    </italic>\n" +
                "                </decorator>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"P:/\\!q. #~\">\n" +
                "                <figure type=\"figure\" id=\"\">\n" +
                "                    <image>7088887067698968</image>\n" +
                "                    <image>728186726674707089828986718972868273708078717970737089888390888568867986778767748167658775686582738285777279677169857286</image>\n" +
                "                </figure>\n" +
                "                <figure type=\"equation\" id=\"\">\n" +
                "                    <image>77836766758366718187906780698168</image>\n" +
                "                    <image>ZGVmYXVsdA==</image>\n" +
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
                "            </publication>\n" +
                "        </reference>\n" +
                "    </references>\n" +
                "</scientific-paper>\n";

        String id = scientificPaperService.create(scientificPaper);

        String found = scientificPaperService.findOneHTML(id);

        System.out.println(found);
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }

}
