package xmlteam4.Project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ReviewServiceTests {

    @Autowired
    ReviewService reviewService;

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
                "    </review-metadata>\n" +
                "    <questionnaire>\n" +
                "        <question type=\"text\">\n" +
                "            <question-text>question-text0</question-text>\n" +
                "            <answer>answer0</answer>\n" +
                "        </question>\n" +
                "        <question type=\"multiple-choice\">\n" +
                "            <question-text>question-text1</question-text>\n" +
                "            <answer selected='true'>answer1</answer>\n" +
                "            <answer selected='false'>answer2</answer>\n" +
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

}
