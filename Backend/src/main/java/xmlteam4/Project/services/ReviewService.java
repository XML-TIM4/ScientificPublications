package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import xmlteam4.Project.repositories.CoverLetterRepository;
import xmlteam4.Project.repositories.ReviewRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformer.DocumentXMLTransformer;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    //@Autowired
    //private XSLTransfomer xslTransfomer;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    @Value("${review-schema-path}")
    private String reviewSchemaPath;

    public String findOne(String id) throws Exception {
        String review = reviewRepository.findOne(id);
        if(review == null) {
            throw new NotFoundException(String.format("Review with id %s is not found", id));
        }

        return review;

    }

    public String create(String scientificPaperId, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, reviewSchemaPath);

        String id = IDGenerator.createID();
        document.getDocumentElement().setAttribute("id", id);

       // document.getElementsByTagName("scientific-paper-reference").item(0).setTextContent(scientificPaperId);

        String newXml = documentXMLTransformer.toXMLString(document);

        return reviewRepository.create(id, newXml);
    }

}