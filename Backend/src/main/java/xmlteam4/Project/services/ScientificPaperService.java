package xmlteam4.Project.services;

import org.apache.xpath.operations.Bool;
import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import xmlteam4.Project.repositories.ScientificPaperRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformer.DocumentXMLTransformer;

@Service
public class ScientificPaperService {

    @Autowired
    private ScientificPaperRepository scientificPaperRepository;

    @Autowired
    private ScientificPaperService scientificPaperService;

    //@Autowired
    //private XSLTransfomer xslTransfomer;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    @Value("${scientific-paper-schema-path}")
    private String scientificPaperSchemaPath;

    public String findOne(String id) throws Exception {
        String paper = scientificPaperService.findOne(id);
        if(paper == null) {
            throw new NotFoundException(String.format("Scientific paper with id %s is not found", id));
        }

        return paper;

    }

    public String create(String xml) throws Exception {
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        String id = IDGenerator.createID();
        document.getDocumentElement().setAttribute("id", id);

        String newXml = documentXMLTransformer.toXMLString(document);

        return scientificPaperRepository.create(id, newXml);
    }

    public String update(String id, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, scientificPaperSchemaPath);

        document.getDocumentElement().setAttribute("id", id);

        String newXml = documentXMLTransformer.toXMLString(document);

        return scientificPaperRepository.update(id, newXml);
    }

    public Boolean delete(String id) throws Exception {
        if(scientificPaperRepository.findOne(id) == null){
               return false;
        }
        scientificPaperRepository.delete(id);
        return true;
    }
}
