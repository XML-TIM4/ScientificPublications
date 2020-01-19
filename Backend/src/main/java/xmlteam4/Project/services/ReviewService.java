package xmlteam4.Project.services;

import org.exist.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import xmlteam4.Project.repositories.ReviewRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformer.XSLTransformer;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    @Autowired
    private XSLTransformer xslTransformer;

    @Value("${review-schema-path}")
    private String reviewSchemaPath;

    public String findOne(String id) throws Exception {
        String review = reviewRepository.findOne(id);
        if(review == null) {
            throw new NotFoundException(String.format("Review with id %s is not found", id));
        }

        return review;
    }

    public String findOneHTML(String id) throws Exception {
        String review = reviewRepository.findOne(id);
        if(review == null) {
            throw new NotFoundException(String.format("Review with id %s is not found", id));
        }
        // transformation to be added later. code saved for future reference
        String rHTML = xslTransformer.generateHTML(review, "data/xsl/xsl-t/Review.xsl");
        return rHTML;
    }

    public String create(String scientificPaperId, String xml) throws Exception {
        Document document = domParser.buildDocument(xml, reviewSchemaPath);

        String id = IDGenerator.createID();
        document.getDocumentElement().setAttribute("id", id);

        Node reviewer = document.getElementsByTagName("reviewer").item(0);
        IDGenerator.generateChildlessElementID(reviewer, id + "/reviewer", "reviewer");

        String newXml = documentXMLTransformer.toXMLString(document);

        return reviewRepository.create(id, newXml);
    }

}
