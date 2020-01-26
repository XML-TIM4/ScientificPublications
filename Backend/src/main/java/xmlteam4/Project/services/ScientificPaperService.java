package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xmlteam4.Project.exceptions.BadParametersException;
import xmlteam4.Project.repositories.ScientificPaperRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformer.XSLTransformer;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScientificPaperService {
    @Autowired
    private ScientificPaperRepository scientificPaperRepository;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    @Autowired
    private XSLTransformer xslTransformer;

    @Value("${scientific-paper-schema-path}")
    private String scientificPaperSchemaPath;

    @Value("${grddl-xslt}")
    private String grddl;

    @Value("${scientific-paper-rdfa-xslt}")
    private String scientificPaperToRDFa;

    @Value("${scientific-paper-html-xslt}")
    private String scientificPaperToHTML;


    public String findOne(String id) throws Exception {
        String paper = scientificPaperRepository.findOne(id);

        if (paper == null) {
            throw new NotFoundException(String.format("Scientific paper with id %s is not found", id));
        }
        return paper;
    }

    public String findOneHTML(String id) throws Exception {
        return xslTransformer.generateHTML(findOne(id), scientificPaperToHTML);
    }

    public String create(String xml) throws Exception {
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        String id = IDGenerator.createID();
        setIDs(id, document);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "Z";
        document.getElementsByTagName("received").item(0).setTextContent(date);

        document.getElementsByTagName("version").item(0).setTextContent("1.0");

        String rdfa = xslTransformer.generateHTML(documentXMLTransformer.toXMLString(document),
                scientificPaperToRDFa);

        String turtle = xslTransformer.generateHTML(rdfa, grddl);
        System.out.println(turtle);

        return scientificPaperRepository.create(id, rdfa);
    }

    public String update(String id, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        setIDs(id, document);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "Z";
        document.getElementsByTagName("received").item(0).setTextContent(date);

        double version = Double.parseDouble(document.getElementsByTagName("version").item(0).getTextContent());
        document.getElementsByTagName("version").item(0).setTextContent(version + 1.0 + "");


        String newXml = documentXMLTransformer.toXMLString(document);

        return scientificPaperRepository.update(id, newXml);
    }

    public Boolean delete(String id) throws Exception {
        if (scientificPaperRepository.findOne(id) == null) {
            return false;
        }
        scientificPaperRepository.delete(id);
        return true;
    }

    private void setIDs(String id, Document document) throws BadParametersException {
        document.getDocumentElement().setAttribute("id", id);

        NodeList authors = document.getElementsByTagName("author");
        for (int i = 0; i < authors.getLength(); ++i) {
            IDGenerator.generateChildlessElementID(authors.item(i), id + "/authors/" + (i + 1), "author");
        }

        Node abstr = document.getElementsByTagName("abstract").item(0);
        IDGenerator.generateChildlessElementID(abstr, id + "/abstract", "abstract");

        NodeList sections = document.getElementsByTagName("section");
        for (int i = 0; i < sections.getLength(); ++i) {
            IDGenerator.generateSectionID(sections.item(i), id + "/sections/" + (i + 1));
        }
    }
}
