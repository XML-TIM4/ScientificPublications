package xmlteam4.Project.utilities.transformers.xslttransformer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import xmlteam4.Project.exceptions.TransformationException;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class XSLTransformerTests {
    private final String first = "<review xmlns=\"https://github.com/XML-TIM4/ScientificPublications\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" id=\"2pgUTygIJxi2E8QFTU2ZD2\" language=\"en\" xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/data/schemas/Review.xsd\">\n" +
            "    <review-metadata>\n" +
            "        <reviewer>\n" +
            "            <name>\n" +
            "                <first-name>first-name0</first-name>\n" +
            "                <last-name>last-name0</last-name>\n" +
            "            </name>\n" +
            "            <email>admin1@admin.com</email>\n" +
            "            <affiliation>\n" +
            "                <university>university0</university>\n" +
            "                <city>city0</city>\n" +
            "                <state>state0</state>\n" +
            "                <country>country0</country>\n" +
            "            </affiliation>\n" +
            "        </reviewer>\n" +
            "        <date>2006-05-04</date>\n" +
            "        <recommendation>accept</recommendation>\n" +
            "        <scientific-paper-id>4C1G8zaEDcR6YyQlt148AM</scientific-paper-id>\n" +
            "    </review-metadata>\n" +
            "    <questionnaire>\n" +
            "        <question>\n" +
            "            <question-text>question-text0</question-text>\n" +
            "        </question>\n" +
            "        <question>\n" +
            "            <question-text>question-text1</question-text>\n" +
            "        </question>\n" +
            "    </questionnaire>\n" +
            "    <reviewer-comments>\n" +
            "    </reviewer-comments>\n" +
            "    <editor-comments>\n" +
            "    </editor-comments>\n" +
            "</review>";

    private final String second = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<review xmlns=\"https://github.com/XML-TIM4/ScientificPublications\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" id=\"2pgUTygIJxi2E8QFTU2ZD2\" language=\"en\" xsi:schemaLocation=\"https://github.com/XML-TIM4/ScientificPublications file:/data/schemas/Review.xsd\">\n" +
            "    <review-metadata>\n" +
            "        <reviewer>\n" +
            "            <name>\n" +
            "                <first-name>first-name0</first-name>\n" +
            "                <last-name>last-name0</last-name>\n" +
            "            </name>\n" +
            "            <email>admin2@admin.com</email>\n" +
            "            <affiliation>\n" +
            "                <university>university0</university>\n" +
            "                <city>city0</city>\n" +
            "                <state>state0</state>\n" +
            "                <country>country0</country>\n" +
            "            </affiliation>\n" +
            "        </reviewer>\n" +
            "        <date>2006-05-04</date>\n" +
            "        <recommendation>accept</recommendation>\n" +
            "        <scientific-paper-id>4C1G8zaEDcR6YyQlt148AM</scientific-paper-id>\n" +
            "    </review-metadata>\n" +
            "    <questionnaire>\n" +
            "        <question>\n" +
            "            <question-text>question-text0</question-text>\n" +
            "        </question>\n" +
            "        <question>\n" +
            "            <question-text>question-text1</question-text>\n" +
            "        </question>\n" +
            "    </questionnaire>\n" +
            "    <reviewer-comments>\n" +
            "    </reviewer-comments>\n" +
            "    <editor-comments>\n" +
            "    </editor-comments>\n" +
            "</review>";

    @Autowired
    private XSLTransformer transformer;

    private final String mergeReviewsXSL = "data/xsl/xsl-t/MergeReviews.xsl";

    @Test
    public void mergeReviews() throws TransformationException {
        assertNotNull(transformer);
        System.out.println(transformer.mergeReviews(first, second, mergeReviewsXSL));
        assertTrue(true);
    }
}
