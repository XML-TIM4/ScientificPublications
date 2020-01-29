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


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ReviewServiceTests {

    @Autowired
    ReviewService reviewService;

    @Autowired
    XSLTransformer xslTransformer;

    @Test
    void testXSLT() throws Exception {
        String review = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<review xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/Backend/data/schemas/Review.xsd\" language=\"en\" id=\"b9\">\n" +
                "    <review-metadata>\n" +
                "        <reviewer id=\"FntmDZMKwrOZT12LIBtROE0\">\n" +
                "            <name>\n" +
                "                <first-name>jeCxMT5Pahlv5KysK8RfI</first-name>\n" +
                "                <middle-name>C9gi6XhF9</middle-name>\n" +
                "                <middle-name>fnhZ8cPxbEuyNAOSePK3_ppGH2L2PC8MVhiZZ9oor3vlL7pG</middle-name>\n" +
                "                <last-name>YbshLWN1tdzYOvtm7HFUE6E4I6FT3BUuhVysd29-LVZlGI.Lw</last-name>\n" +
                "            </name>\n" +
                "            <email>Ú@~V +.ara</email>\n" +
                "            <affiliation>\n" +
                "                <university>or32BfZP8fIFw83hd3YsvtYyA</university>\n" +
                "                <city>oXy</city>\n" +
                "                <state>b4Loy0ssNdyU_4nSs.AHDg36Hkhdgv8XZHUj</state>\n" +
                "                <country>PxC</country>\n" +
                "            </affiliation>\n" +
                "        </reviewer>\n" +
                "        <date>2021-05-23Z</date>\n" +
                "        <recommendation>C7oXx01exQcjQ</recommendation>\n" +
                "        <scientific-paper-id>j.xBOPweWUrCQRo0e.</scientific-paper-id>\n" +
                "    </review-metadata>\n" +
                "    <questionnaire>\n" +
                "        <question type=\"text\">\n" +
                "            <question-text>koXHcI3oScOP0G</question-text>\n" +
                "            <answer selected=\"0\">\n" +
                "            </answer>\n" +
                "            <answer selected=\"true\">\n" +
                "            </answer>\n" +
                "        </question>\n" +
                "        <question type=\"text\">\n" +
                "            <question-text>A23FsHnIy86L3exsmaaX.z6_</question-text>\n" +
                "            <answer selected=\"false\">\n" +
                "            </answer>\n" +
                "            <answer selected=\"false\">\n" +
                "            </answer>\n" +
                "        </question>\n" +
                "    </questionnaire>\n" +
                "    <author-comments>\n" +
                "        <author-comment>\n" +
                "            <review-reference>dY2mfF8y3WcL.Wg6Li5cD_uIh</review-reference>\n" +
                "            <comment-text>ukrO1fx02-LlDwzh6N9yswCe-Gmb6whlIZ0JI6hztSXupMfLPkv3</comment-text>\n" +
                "        </author-comment>\n" +
                "        <author-comment>\n" +
                "            <review-reference>m7YUyyqurAA4BVGFZGXKg</review-reference>\n" +
                "            <comment-text>Mwe6TPQXzfhHgqP880P8GpkHY8d7VdG78m_cEjRPVe81wyIB65qTadrv8PP8uHwu5J</comment-text>\n" +
                "        </author-comment>\n" +
                "    </author-comments>\n" +
                "    <editor-comments>\n" +
                "        <editor-comment>\n" +
                "            <review-reference>p-</review-reference>\n" +
                "            <comment-text>cqSVJ0HVdRnRtyOMzipfMYh4dqdb.Pd9Q38ODNDacoRGcVwcpbXvTa2GGJxZ-uGlIRCiV8DFx2jT9mFaygd9DRu3wttUPQ</comment-text>\n" +
                "        </editor-comment>\n" +
                "        <editor-comment>\n" +
                "            <review-reference>C5ftoj1J</review-reference>\n" +
                "            <comment-text>GcND0LuOwzWyw-ElfSeM7xGX0WUihbutwvtKUAD8_SdSQOzmwGBMlwTgxVLkVrt_ZhTvPa-lbgdMT</comment-text>\n" +
                "        </editor-comment>\n" +
                "    </editor-comments>\n" +
                "</review>\n";

        String result = xslTransformer.generateHTML(review, "D:\\ScientificPublications\\Backend\\data\\xsl" +
                "\\xsl-t\\ReviewToRDFa.xsl");

        System.out.println(result);

        assertTrue(true);
    }

    @Test
    void create_validReview_equals() throws Exception {
        String review = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<review xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/XMLSchemas/Review.xsd\">\n" +
                "    <review-metadata>\n" +
                "        <reviewer>\n" +
                "            <name>\n" +
                "                <first-name>first-name0</first-name>\n" +
                "                <last-name>last-name0</last-name>\n" +
                "            </name>\n" +
                "            <email> @ .a</email>\n" +
                "            <affiliation>\n" +
                "                <university>university0</university>\n" +
                "                <city>city0</city>\n" +
                "                <state>state0</state>\n" +
                "                <country>country0</country>\n" +
                "            </affiliation>\n" +
                "        </reviewer>\n" +
                "        <date>2006-05-04</date>\n" +
                "        <recommendation>accept</recommendation>\n" +
                "    </review-metadata>\n" +
                "    <questionnaire>\n" +
                "        <question>\n" +
                "            <question-text>question-text0</question-text>\n" +
                "        </question>\n" +
                "        <question>\n" +
                "            <question-text>question-text1</question-text>\n" +
                "        </question>\n" +
                "    </questionnaire>\n" +
                "    <author-comments>\n" +
                "        <author-comment>\n" +
                "            <review-reference>review-reference0</review-reference>\n" +
                "            <comment-text>comment-text0</comment-text>\n" +
                "        </author-comment>\n" +
                "        <author-comment>\n" +
                "            <review-reference>review-reference1</review-reference>\n" +
                "            <comment-text>comment-text1</comment-text>\n" +
                "        </author-comment>\n" +
                "    </author-comments>\n" +
                "    <editor-comments>\n" +
                "        <editor-comment>\n" +
                "            <review-reference>review-reference2</review-reference>\n" +
                "            <comment-text>comment-text2</comment-text>\n" +
                "        </editor-comment>\n" +
                "        <editor-comment>\n" +
                "            <review-reference>review-reference3</review-reference>\n" +
                "            <comment-text>comment-text3</comment-text>\n" +
                "        </editor-comment>\n" +
                "    </editor-comments>\n" +
                "</review>\n";

        String scientificPaperId = IDGenerator.createID();

        String id = reviewService.create(scientificPaperId, review);

        String found = reviewService.findOne(id);

        System.out.println(found);
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }

    @Test
    void create_validReview_equals2() throws Exception {
        String review = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<review xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/XMLSchemas/Review.xsd\">\n" +
                "    <review-metadata>\n" +
                "        <reviewer>\n" +
                "            <name>\n" +
                "                <first-name>first-name0</first-name>\n" +
                "                <last-name>last-name0</last-name>\n" +
                "            </name>\n" +
                "            <email> @ .a</email>\n" +
                "            <affiliation>\n" +
                "                <university>university0</university>\n" +
                "                <city>city0</city>\n" +
                "                <state>state0</state>\n" +
                "                <country>country0</country>\n" +
                "            </affiliation>\n" +
                "        </reviewer>\n" +
                "        <date>2006-05-04</date>\n" +
                "        <recommendation>accept</recommendation>\n" +
                "        <scientific-paper-id>1111111111111111111</scientific-paper-id>\n" +
                "    </review-metadata>\n" +
                "    <questionnaire>\n" +
                "        <question type=\"text\">\n" +
                "            <question-text>question-text0</question-text>\n" +
                "            <answer>answer0</answer>\n" +
                "        </question>\n" +
                "        <question type=\"multiple-choice\">\n" +
                "            <question-text>question-text1</question-text>\n" +
                "            <answer selected=\"true\">answer1</answer>\n" +
                "            <answer selected=\"false\">answer2</answer>\n" +
                "        </question>\n" +
                "    </questionnaire>\n" +
                "    <author-comments>\n" +
                "        <author-comment>\n" +
                "            <review-reference>review-reference0</review-reference>\n" +
                "            <comment-text>comment-text0</comment-text>\n" +
                "        </author-comment>\n" +
                "        <author-comment>\n" +
                "            <review-reference>review-reference1</review-reference>\n" +
                "            <comment-text>comment-text1</comment-text>\n" +
                "        </author-comment>\n" +
                "    </author-comments>\n" +
                "    <editor-comments>\n" +
                "        <editor-comment>\n" +
                "            <review-reference>review-reference2</review-reference>\n" +
                "            <comment-text>comment-text2</comment-text>\n" +
                "        </editor-comment>\n" +
                "        <editor-comment>\n" +
                "            <review-reference>review-reference3</review-reference>\n" +
                "            <comment-text>comment-text3</comment-text>\n" +
                "        </editor-comment>\n" +
                "    </editor-comments>\n" +
                "</review>\n";

        String scientificPaperId = IDGenerator.createID();

        String id = reviewService.create(scientificPaperId, review);

        String found = reviewService.findOneHTML(id);

        System.out.println(found);
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }


    @Test
    void create_validReviewPDF_equals() throws Exception {
        String review = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<review xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/D:/ScientificPublications/XMLSchemas/Review.xsd\">\n" +
                "    <review-metadata>\n" +
                "        <reviewer>\n" +
                "            <name>\n" +
                "                <first-name>first-name0</first-name>\n" +
                "                <last-name>last-name0</last-name>\n" +
                "            </name>\n" +
                "            <email> @ .a</email>\n" +
                "            <affiliation>\n" +
                "                <university>university0</university>\n" +
                "                <city>city0</city>\n" +
                "                <state>state0</state>\n" +
                "                <country>country0</country>\n" +
                "            </affiliation>\n" +
                "        </reviewer>\n" +
                "        <date>2006-05-04</date>\n" +
                "        <recommendation>accept</recommendation>\n" +
                "        <scientific-paper-id>42fasf234</scientific-paper-id>\n" +
                "    </review-metadata>\n" +
                "    <questionnaire>\n" +
                "        <question type=\"text\">\n" +
                "            <question-text>question-text0</question-text>\n" +
                "            <answer>answer0</answer>\n" +
                "        </question>\n" +
                "        <question type=\"multiple-choice\">\n" +
                "            <question-text>question-text1</question-text>\n" +
                "            <answer selected=\"true\">answer1</answer>\n" +
                "            <answer selected=\"false\">answer2</answer>\n" +
                "        </question>\n" +
                "    </questionnaire>\n" +
                "    <author-comments>\n" +
                "        <author-comment>\n" +
                "            <review-reference>review-reference0</review-reference>\n" +
                "            <comment-text>comment-text0</comment-text>\n" +
                "        </author-comment>\n" +
                "        <author-comment>\n" +
                "            <review-reference>review-reference1</review-reference>\n" +
                "            <comment-text>comment-text1</comment-text>\n" +
                "        </author-comment>\n" +
                "    </author-comments>\n" +
                "    <editor-comments>\n" +
                "        <editor-comment>\n" +
                "            <review-reference>review-reference2</review-reference>\n" +
                "            <comment-text>comment-text2</comment-text>\n" +
                "        </editor-comment>\n" +
                "        <editor-comment>\n" +
                "            <review-reference>review-reference3</review-reference>\n" +
                "            <comment-text>comment-text3</comment-text>\n" +
                "        </editor-comment>\n" +
                "    </editor-comments>\n" +
                "</review>\n";

        String scientificPaperId = IDGenerator.createID();

        String id = reviewService.create(scientificPaperId, review);

        String found = reviewService.findOne(id);

        ByteArrayOutputStream stream = xslTransformer.generatePDF(found, "data/xsl/xsl-fo/ReviewToPDF.xsl");
        try(OutputStream outputStream = new FileOutputStream("review.pdf")) {
            stream.writeTo(outputStream);
        }
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }
}
