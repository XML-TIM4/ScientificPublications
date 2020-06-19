package xmlteam4.Project.services;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import xmlteam4.Project.businessprocess.*;
import xmlteam4.Project.exceptions.BusinessProcessException;
import xmlteam4.Project.exceptions.DocumentParsingFailedException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.exceptions.TransformationException;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.repositories.ReviewRepository;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;
import xmlteam4.Project.utilities.transformers.documentxmltransformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import javax.mail.MessagingException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.util.List;

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

    @Autowired
    private IDGenerator idGenerator;

    @Autowired
    private BusinessProcessService businessProcessService;

    @Autowired
    private ReviewerService reviewerService;

    @Autowired
    private ScientificPaperService scientificPaperService;

    @Autowired
    private NotificationService notificationService;

    @Value("${review-schema-path}")
    private String reviewSchemaPath;

    @Value("${scientific-paper-schema-path}")
    private String scientificPaperSchemaPath;

    @Value("${review-to-html-xslt}")
    private String reviewToHTML;

    @Value("${review-to-pdf-xslfo}")
    private String reviewToPDF;

    @Value("${merge-reviews-xslt}")
    private String mergeReviewsXslt;


    public String getReviewXML(String id) throws RepositoryException {
        return reviewRepository.findOne(id);
    }

    public String getReviewHTML(String id) throws RepositoryException, TransformationException {
        return xslTransformer.generateXML(getReviewXML(id), reviewToHTML);
    }

    public InputStreamResource getReviewPDF(String id) throws RepositoryException, TransformationException {
        return new InputStreamResource(new ByteArrayInputStream(xslTransformer.generatePDF(getReviewXML(id),
                reviewToPDF).toByteArray()));
    }

    public String createReviewTemplate(String xml) throws DocumentParsingFailedException, RepositoryException,
            BusinessProcessException, TransformationException, TransformerException, MessagingException {
        Document document = domParser.buildDocument(xml, reviewSchemaPath);

        // extract paper id
        String scientificPaperId = document.getElementsByTagName("scientific-paper-id").item(0).getTextContent();

        if (scientificPaperId == null)
            throw new DocumentParsingFailedException("Review is missing scientific paper id");

        TBusinessProcess businessProcess = businessProcessService.findById(scientificPaperId);

        TReviewCycle activeCycle = businessProcessService.getActiveCycle(businessProcess);

        // check if cycle is active
        if (!activeCycle.getStatus().equals(ReviewCycleStatus.PENDING.toString()))
            throw new BusinessProcessException("Review can be created only if review cycle is still active");

        TPhase activePhase = businessProcessService.getActivePhase(activeCycle);
        TUser loggedIn = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // active phase has to be submitted, author has created cover letter before and review template task is
        // not finished
        if (!(activePhase.getTitle().equals(PhaseTitle.SUBMITTED.toString())
                && businessProcessService.getTaskByDocumentType(activePhase, DocumentType.COVER_LETTER).isFinished()
                && !businessProcessService.getTaskByDocumentType(activePhase, DocumentType.REVIEW).isFinished())) {
            throw new BusinessProcessException("Wrong review cycle phase or task is already completed");
        }
        // create ids
        String id = idGenerator.createID();
        document.getDocumentElement().setAttribute("id", id);

        // choose reviewers

        String theXML = scientificPaperService.getScientificPaperXML(scientificPaperId);
        Document scientificPaper = domParser.buildDocument(theXML, scientificPaperSchemaPath);

        Pair<List<String>, String> requiredData =
                scientificPaperService.extractDataForSelectionOfReviewers(scientificPaper);

        Pair<TUser, TUser> chosenReviewers = reviewerService.selectReviewers(requiredData.getKey(),
                requiredData.getValue());

        // make review phase
        TPhase reviewPhase = businessProcessService.createReviewPhase(chosenReviewers.getKey().getId(),
                chosenReviewers.getValue().getId());

        // finish all tasks in submitted phase and set can advance true
        activePhase.setCanAdvance(true);

        TActorTask createReviewTemplateTask = businessProcessService.getTaskByDocumentType(activePhase,
                DocumentType.REVIEW);
        createReviewTemplateTask.setUserId(loggedIn.getId());
        createReviewTemplateTask.setDocumentId(id);
        createReviewTemplateTask.setFinished(true);

        activeCycle.getPhases().getPhase().add(reviewPhase);

        // update business process
        businessProcessService.updateBusinessProcess(businessProcess);

        // notify reviewers
        notificationService.notifyUser(chosenReviewers.getKey(), id, DocumentType.REVIEW,
                "Your review has been requested.");
        notificationService.notifyUser(chosenReviewers.getValue(), id, DocumentType.REVIEW,
                "Your review has been requested.");

        return reviewRepository.create(id, documentXMLTransformer.toXMLString(document));
    }

    public String createReview(String xml) throws DocumentParsingFailedException, RepositoryException, BusinessProcessException, TransformationException, TransformerException, MessagingException {
        Document document = domParser.buildDocument(xml, reviewSchemaPath);

        // extract template id
        String reviewTemplateId =
                document.getElementsByTagName("review").item(0).getAttributes().getNamedItem("id").getTextContent();

        if (reviewTemplateId == null)
            throw new DocumentParsingFailedException("Review is missing template id");

        // extract paper id
        String scientificPaperId = document.getElementsByTagName("scientific-paper-id").item(0).getTextContent();

        if (scientificPaperId == null)
            throw new DocumentParsingFailedException("Review is missing scientific paper id");

        TBusinessProcess businessProcess = businessProcessService.findById(scientificPaperId);

        TReviewCycle activeCycle = businessProcessService.getActiveCycle(businessProcess);

        // check if cycle is active
        if (!activeCycle.getStatus().equals(ReviewCycleStatus.PENDING.toString()))
            throw new BusinessProcessException("Review can be created only if review cycle is still active");

        TPhase activePhase = businessProcessService.getActivePhase(activeCycle);
        TUser loggedIn = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // check if phase is review and task hasn't been completed
        if (!(activePhase.getTitle().equals(PhaseTitle.REVIEW.toString())
                && !businessProcessService.getTaskByDocumentTypeAndUserId(activePhase, DocumentType.REVIEW,
                loggedIn.getId()).isFinished())) {
            throw new BusinessProcessException("Wrong review cycle phase or task is already completed");
        }

        if (businessProcessService.getTaskByDocumentTypeAndDifferentUserId(activePhase, DocumentType.REVIEW,
                loggedIn.getId()).isFinished()) {
            // load temp
            String temp = reviewRepository.findOneWithReviwer(reviewTemplateId);

            // merge
            String merged = xslTransformer.mergeReviews(temp, xml, mergeReviewsXslt);

            // update template as completed review
            reviewRepository.update(reviewTemplateId, merged);

            // can advance to next phase
            activePhase.setCanAdvance(true);

            // status is reviewed
            activeCycle.setStatus(ReviewCycleStatus.REVIEWED.toString());
        } else {
            // create temp
            reviewRepository.create(reviewTemplateId + "-temp", xml);
        }

        // complete task
        TActorTask createReview = businessProcessService
                .getTaskByDocumentTypeAndUserId(activePhase, DocumentType.REVIEW,
                        loggedIn.getId());
        createReview.setFinished(true);
        createReview.setDocumentId(reviewTemplateId);

        // save business process
        businessProcessService.updateBusinessProcess(businessProcess);

        // notify reviewer
        notificationService
                .notifyUser(loggedIn, reviewTemplateId, DocumentType.REVIEW, "Thank you for submitted review!");

        return reviewTemplateId;
    }

    public String getReviewId(String scientificPaperId) throws RepositoryException {
        return reviewRepository.findOneByPaperId(scientificPaperId);
    }
}
