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

public class ReviewServiceTests {

    @Autowired
    ReviewService reviewService;

    @Autowired
    IDGenerator idGenerator;

    @Autowired
    XSLTransformer xslTransformer;

    @Test
    void testXSLT() throws Exception {
        String review = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<review xmlns=\"https://github.com/XML-TIM4/ScientificPublications\"\n" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                " xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/data/schemas/Review.xsd\">\n" +
                "    <review-metadata>\n" +
                "        <reviewer>\n" +
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
                "        </reviewer>\n" +
                "        <date>2006-05-04</date>\n" +
                "        <recommendation>accept</recommendation>\n" +
                "        <scientific-paper-id>asdfqwer</scientific-paper-id>\n" +
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

        String result = xslTransformer.generateHTML(review, ".\\data\\xsl" +
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
                "            <email>admin@admin.com</email>\n" +
                "            <affiliation>\n" +
                "                <university>university0</university>\n" +
                "                <city>city0</city>\n" +
                "                <state>state0</state>\n" +
                "                <country>country0</country>\n" +
                "            </affiliation>\n" +
                "        </reviewer>\n" +
                "        <date>2006-05-04</date>\n" +
                "        <recommendation>accept</recommendation>\n" +
                "        <scientific-paper-id>asdfqwer</scientific-paper-id>\n" +
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

        String scientificPaperId = idGenerator.createID();

        String id = TARGET_NAMESPACE + "/reviews/" + reviewService.create(scientificPaperId, review);

        String found = reviewService.getReviewXML(id);

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
                "            <email>admin@admin.com</email>\n" +
                "            <affiliation>\n" +
                "                <university>university0</university>\n" +
                "                <city>city0</city>\n" +
                "                <state>state0</state>\n" +
                "                <country>country0</country>\n" +
                "            </affiliation>\n" +
                "        </reviewer>\n" +
                "        <date>2006-05-04</date>\n" +
                "        <recommendation>accept</recommendation>\n" +
                "        <scientific-paper-id>asdfqwer</scientific-paper-id>\n" +
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

        String scientificPaperId = idGenerator.createID();

        String id = TARGET_NAMESPACE + "/reviews/" + reviewService.create(scientificPaperId, review);

        String found = reviewService.getReviewHTML(id);

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
                "            <email>admin@admin.com</email>\n" +
                "            <affiliation>\n" +
                "                <university>university0</university>\n" +
                "                <city>city0</city>\n" +
                "                <state>state0</state>\n" +
                "                <country>country0</country>\n" +
                "            </affiliation>\n" +
                "        </reviewer>\n" +
                "        <date>2006-05-04</date>\n" +
                "        <recommendation>accept</recommendation>\n" +
                "        <scientific-paper-id>asdfqwer</scientific-paper-id>\n" +
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

        String scientificPaperId = idGenerator.createID();

        String id = TARGET_NAMESPACE + "/reviews/" + reviewService.create(scientificPaperId, review);

        String found = reviewService.getReviewXML(id);

        ByteArrayOutputStream stream = xslTransformer.generatePDF(found, "data/xsl/xsl-fo/ReviewToPDF.xsl");
        try(OutputStream outputStream = new FileOutputStream("review.pdf")) {
            stream.writeTo(outputStream);
        }
        assertNotNull(found, "Found not null");
        assertFalse(found.equals(""), "Found not empty string");
    }
}
