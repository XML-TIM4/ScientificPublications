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
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/Backend/data/schemas/ScientificPaper.xsd\" language=\"en\" id=\"CQLP7XcE64kYMDEh\">\n" +
                "    <metadata>\n" +
                "        <received>2021-07-02Z</received>\n" +
                "        <revised>2021-07-02Z</revised>\n" +
                "        <accepted>2020-10-10</accepted>\n" +
                "        <version>1.0</version>\n" +
                "        <status>revision</status>\n" +
                "        <category>research-paper</category>\n" +
                "        <keywords>\n" +
                "            <keyword>HdkVdO.QQ</keyword>\n" +
                "            <keyword>BiR7U_hxiaJ3YGhtbv</keyword>\n" +
                "        </keywords>\n" +
                "    </metadata>\n" +
                "    <title>r</title>\n" +
                "    <authors>\n" +
                "        <author id=\"_iR7GNUfn.S.ecC8NXg.VafqH\">\n" +
                "            <name>\n" +
                "                <first-name>AmoF9Z_ldIwe</first-name>\n" +
                "                <middle-name>QpejSb8PUdJWjkBrzkNHD</middle-name>\n" +
                "                <middle-name>SclwT2HtIEo</middle-name>\n" +
                "                <last-name>kg</last-name>\n" +
                "            </name>\n" +
                "            <email>Ú@~V +.ara</email>\n" +
                "            <affiliation>\n" +
                "                <university>r6n_f</university>\n" +
                "                <city>tVkrgzZ0RSWRg7zGvCc1tKXp9EX4hE.ea6Cp</city>\n" +
                "                <state>U9T7.y.CXt7snyCzDks</state>\n" +
                "                <country>AAbh8z-_pYRuvQVkbdXsIEzPRtX3Ore4f54OGBlrYkaxx</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "        <author id=\"waI9ru\">\n" +
                "            <name>\n" +
                "                <first-name>ea.AAtLZDFOX9hWNafuzZByEd4s0vAyRFY4</first-name>\n" +
                "                <middle-name>R39HjxEytLW_0qOqvd-QFY1vcRP6B0tBtnBbXKYxYJyDUSB</middle-name>\n" +
                "                <middle-name>cup5pvi6.hhrRxPUb8mkZq</middle-name>\n" +
                "                <last-name>v9r.m</last-name>\n" +
                "            </name>\n" +
                "            <email>í0;@g.ofn</email>\n" +
                "            <affiliation>\n" +
                "                <university>wiTvrm2.TKBF1F_6ayVvq</university>\n" +
                "                <city>b51HSq1r.o2RakWE_45CsZ7m3tSP</city>\n" +
                "                <state>l</state>\n" +
                "                <country>CsRo</country>\n" +
                "            </affiliation>\n" +
                "        </author>\n" +
                "    </authors>\n" +
                "    <abstract id=\"_tHhOS1XxqD\">\n" +
                "        <abstract-item title=\"Practical Implications\">NP</abstract-item>\n" +
                "        <abstract-item title=\"Methodology\">TsJs_J_l_hUDOZDXDzm</abstract-item>\n" +
                "        <abstract-item title=\"Purpose\">uZ-PNbRWAoJWNRQ5A2.6</abstract-item>\n" +
                "        <abstract-item title=\"Findings\">BM6KB24Rsg-WsuerMc0GM</abstract-item>\n" +
                "    </abstract>\n" +
                "    <content>\n" +
                "        <section id=\"muU4WuwOXy5X_pBEvgrjf5g9iqYcit\">\n" +
                "            <title>oYKxZIe9QSEr-8ZhWTIjl</title>\n" +
                "            <paragraph id=\"ahHsgTP9nZJ4xOQKIyfuxjv\">\n" +
                "                <quote>\n" +
                "                    <quote-text>K0DalYGZUBFTjAQ8E</quote-text>\n" +
                "                    <publication>\n" +
                "                        <title>F79mrXZ.tQyrS69wcfReAxnqa9nuBfycV6G7IZ.eEwOcxi2Z-0tixLaXb8Ee2RHJqXr3xhSHkaf0wpdJHs_T-</title>\n" +
                "                        <publisher>caNx</publisher>\n" +
                "                        <place>D.-hMNckVjbytDoE1Dwevy3E</place>\n" +
                "                    </publication>\n" +
                "                </quote>\n" +
                "                <list ordered=\"false\">\n" +
                "                    <list-item>F.RGdDa7J5QtePkSlniQbHvW</list-item>\n" +
                "                    <list-item>we_dl</list-item>\n" +
                "                </list>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"npolxgXi2fq\">\n" +
                "                <list ordered=\"false\">\n" +
                "                    <list-item>J_8o5doErv2t5uH.N.GE_PBzyF</list-item>\n" +
                "                    <list-item>Gkv6eevYUSoRh7v6eGnvft7l8zWVnU4LfNg.</list-item>\n" +
                "                </list>\n" +
                "                <list ordered=\"false\">\n" +
                "                    <list-item>_dclEmV.</list-item>\n" +
                "                    <list-item>tIHA8HpWqut6EZWCr6ead</list-item>\n" +
                "                </list>\n" +
                "            </paragraph>\n" +
                "        </section>\n" +
                "        <section id=\"IJH2HvPcUKJpQV2vHcC-jbQLTJ\">\n" +
                "            <title>LAgaY6l7vREDANjR</title>\n" +
                "            <paragraph id=\"WnKjYi0CD\">\n" +
                "                <list ordered=\"false\">\n" +
                "                    <list-item>HFHoah8aDBhRopvot1</list-item>\n" +
                "                    <list-item>_pClr2OEAeTzI5ztJ</list-item>\n" +
                "                </list>\n" +
                "                <list ordered=\"false\">\n" +
                "                    <list-item>nGaJbLa-t1XjHNAc67xUECpW.HxUiuYDd850np8</list-item>\n" +
                "                    <list-item>vGJXU.CEKFL5cO9XDuY</list-item>\n" +
                "                </list>\n" +
                "            </paragraph>\n" +
                "            <paragraph id=\"oJu6mYvmrZWJa3oa2E\">\n" +
                "                <quote>\n" +
                "                    <quote-text>r6YjRGyXgUKH</quote-text>\n" +
                "                    <publication>\n" +
                "                        <title>QAt25bFVTGbLRwsW7GIwwoAoE-rd3FZ1CReI</title>\n" +
                "                        <publisher>Ty3klujRgOqH5D2cy2oCSPp</publisher>\n" +
                "                        <place>ARJsqfeJhiS_yFGXRZKX121e195XE3vlycxGm4zOmoay</place>\n" +
                "                    </publication>\n" +
                "                </quote>\n" +
                "                <figure type=\"figure\" id=\"HFez\">\n" +
                "                    <image>7580687690816786678987668268716573767479</image>\n" +
                "                    <image>82706867857569688785656880798082</image>\n" +
                "                </figure>\n" +
                "            </paragraph>\n" +
                "        </section>\n" +
                "    </content>\n" +
                "    <references>\n" +
                "        <reference>\n" +
                "            <authors>\n" +
                "                <name>\n" +
                "                    <first-name>IAbApJtNanDJstMJSsh1q5H_gO-z29reBa8gLonUvKiE</first-name>\n" +
                "                    <middle-name>fu8azAMXrfQGMlUb05MnS9JpAcLl3daH</middle-name>\n" +
                "                    <middle-name>qTc</middle-name>\n" +
                "                    <last-name>tSrSgMxmQLR</last-name>\n" +
                "                </name>\n" +
                "                <name>\n" +
                "                    <first-name>ITe8Se01onEkP46oT5qvbhdrMAn</first-name>\n" +
                "                    <middle-name>l47l_</middle-name>\n" +
                "                    <middle-name>EON5fWENif4vZTXKy_8BOrnZ</middle-name>\n" +
                "                    <last-name>PUAPnOB.x7m2h2jFzbFyfIqJ3Z.um7HYpVNZa6</last-name>\n" +
                "                </name>\n" +
                "            </authors>\n" +
                "            <publication>\n" +
                "                <title>GWFOm4bdpybpVt91OFfpuzlRsU6yask5P04_C3AQTm</title>\n" +
                "                <year>1449</year>\n" +
                "                <publisher>dezuK_UVNV5U7D0MNhF.-JSY</publisher>\n" +
                "                <place>jnJ-3M-BRhKFE8TJP</place>\n" +
                "            </publication>\n" +
                "        </reference>\n" +
                "        <reference>\n" +
                "            <authors>\n" +
                "                <name>\n" +
                "                    <first-name>V0IFY5kY-wqVoC51LiRuifyO_Awsk0Njt0mP</first-name>\n" +
                "                    <middle-name>RiVMrbZP9mgfdysjnBbCrAQgk.vd3.1AEzCd7gUMKH</middle-name>\n" +
                "                    <middle-name>NAoK2pDNBm6Bv-3kb2DOQ</middle-name>\n" +
                "                    <last-name>_M8AlT77BZNPccG6O1Q</last-name>\n" +
                "                </name>\n" +
                "                <name>\n" +
                "                    <first-name>H_oDAxthoHj4cC0VN12j2</first-name>\n" +
                "                    <middle-name>U</middle-name>\n" +
                "                    <middle-name>H.US8</middle-name>\n" +
                "                    <last-name>aC</last-name>\n" +
                "                </name>\n" +
                "            </authors>\n" +
                "            <publication>\n" +
                "                <title>ut7-XDkvc7iHS01__D0auXt5</title>\n" +
                "                <year>1019</year>\n" +
                "                <publisher>M</publisher>\n" +
                "                <place>d7q.KT44gxfzggev8AZAwE6F5383qf2</place>\n" +
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
