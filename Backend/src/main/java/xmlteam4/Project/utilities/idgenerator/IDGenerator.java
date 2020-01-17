package xmlteam4.Project.utilities.idgenerator;

import com.devskiller.friendly_id.FriendlyId;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xmlteam4.Project.exceptions.BadParametersException;

public class IDGenerator {
    public static String createID() {
        return FriendlyId.createFriendlyId();
    }

    public static void generateSectionID(Node node, String id) throws BadParametersException {
        if (node instanceof Element) {
            if (!node.getNodeName().equals("section")) {
                throw new BadParametersException("Expected section, instead got " + node.getNodeName());
            }
            Element section = (Element) node;

            section.setAttribute("id", id);

            NodeList paragraphs = section.getElementsByTagName("paragraph");
            for (int i = 0; i < paragraphs.getLength(); ++i) {
                generateParagraphID(paragraphs.item(i), id + "/paragraphs/" + (i+1));
            }
        }
        else {
            throw new BadParametersException("Node object passed is not an Element");
        }
    }

    public static void generateParagraphID(Node node, String id) throws BadParametersException {
        if (node instanceof Element) {
            if (!node.getNodeName().equals("paragraph")) {
                throw new BadParametersException("Expected paragraph, instead got " + node.getNodeName());
            }
            Element paragraph = (Element) node;

            paragraph.setAttribute("id", id);

            NodeList tables = paragraph.getElementsByTagName("table");
            for (int i = 0; i < tables.getLength(); ++i) {
                generateChildlessElementID(tables.item(i), id + "/tables/" + (i+1),"table");
            }

            NodeList figures = paragraph.getElementsByTagName("figure");
            for (int i = 0; i < figures.getLength(); ++i) {
                generateChildlessElementID(figures.item(i), id + "/figures/" + (i+1), "figure");
            }
        }
        else {
            throw new BadParametersException("Node object passed is not an Element");
        }
    }

    public static void generateChildlessElementID(Node node, String id, String type) throws BadParametersException{
        if (node instanceof Element) {
            if (!node.getNodeName().equals(type)) {
                throw new BadParametersException(String.format("Expected %s, instead got %s", type, node.getNodeName()));
            }
            Element element = (Element) node;

            element.setAttribute("id", id);
        }
        else {
            throw new BadParametersException("Node object passed is not an Element");
        }
    }
}
