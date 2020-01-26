package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xmlteam4.Project.exceptions.BadParametersException;
import xmlteam4.Project.exceptions.DocumentParsingFailedException;
import xmlteam4.Project.model.ScientificPaperAbstractTitles;
import xmlteam4.Project.model.ScientificPaperStatus;
import xmlteam4.Project.repositories.ScientificPaperRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformer.XSLTransformer;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${scientific-paper-schema-path}")
    private String scientificPaperSchemaPath;

    @Value("${grddl-xslt}")
    private String grddl;

    @Value("${scientific-paper-rdfa-xslt}")
    private String scientificPaperToRDFa;

    @Value("${scientific-paper-html-xslt}")
    private String scientificPaperToHTML;

    @Value("${scientific-paper-pdf-xslfo}")
    private String scientificPaperToPDF;


    public String getScientificPaperXML(String id) throws Exception {
        String paper = scientificPaperRepository.findOne(id);

        if (paper == null) {
            throw new NotFoundException(String.format("Scientific paper with id %s is not found", id));
        }
        return paper;
    }

    public String getScientificPaperHTML(String id) throws Exception {
        return xslTransformer.generateHTML(getScientificPaperXML(id), scientificPaperToHTML);
    }

    public InputStreamResource getScientificPaperPDF(String id) throws Exception {
        return new InputStreamResource(new ByteArrayInputStream(xslTransformer.generatePDF(getScientificPaperXML(id),
                scientificPaperToPDF).toByteArray()));
    }

    public String createScientificPaper(String xml) throws Exception {
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        String id = IDGenerator.createID();
        setIDs(id, document);

        if (!checkAbstract(document))
            throw new DocumentParsingFailedException("Invalid abstract titles");

        document.getElementsByTagName("received").item(0).setTextContent(dateTimeFormatter.format(LocalDateTime.now()));

        document.getElementsByTagName("status").item(0)
                .setTextContent(ScientificPaperStatus.UPLOADED.toString());

        String rdfa = xslTransformer.generateHTML(documentXMLTransformer.toXMLString(document),
                scientificPaperToRDFa);

        String turtle = xslTransformer.generateHTML(rdfa, grddl);
        System.out.println(turtle);

        return scientificPaperRepository.create(id, rdfa);
    }

    public String reviseScientificPaper(String id, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        setIDs(id, document);

        Document oldDocument = domParser.buildDocument(getScientificPaperXML(id), scientificPaperSchemaPath);

        if (!checkAbstract(document))
            throw new DocumentParsingFailedException("Invalid abstract titles");

        document.getElementsByTagName("received").item(0)
                .setTextContent(oldDocument.getElementsByTagName("received").item(0).getTextContent());

        document.getElementsByTagName("revised").item(0).setTextContent(dateTimeFormatter.format(LocalDateTime.now()));

        document.getElementsByTagName("status").item(0)
                .setTextContent(ScientificPaperStatus.REVISION.toString());

        double version = Double.parseDouble(document.getElementsByTagName("version").item(0).getTextContent()) + 1.0;
        document.getElementsByTagName("version").item(0).setTextContent(Double.toString(version));

        String newXml = documentXMLTransformer.toXMLString(document);

        return scientificPaperRepository.update(id, newXml);
    }

    public Boolean withdrawScientificPaper(String id) throws Exception {
        String paper = getScientificPaperXML(id);

        Document document = domParser.buildDocument(paper, scientificPaperSchemaPath);

        document.getElementsByTagName("status").item(0)
                .setTextContent(ScientificPaperStatus.WITHDRAWN.toString());

        scientificPaperRepository.update(id, documentXMLTransformer.toXMLString(document));
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

    private boolean checkAbstract(Document document) {
        ArrayList<String> abstractTitles = new ArrayList<>();
        NodeList abstractItems = document.getElementsByTagName("abstract-item");

        for (int i = 0; i < abstractItems.getLength(); i++) {
            abstractTitles.add(abstractItems.item(i).getAttributes().getNamedItem("title").getTextContent());
        }

        Set<String> uniqueTitles = new HashSet<>(abstractTitles);

        if (uniqueTitles.size() != abstractTitles.size()) {
            return false;
        }

        return uniqueTitles.containsAll(Arrays.stream(ScientificPaperAbstractTitles.values())
                .filter(ScientificPaperAbstractTitles::isMandatory).collect(Collectors.toList()));
    }
}
