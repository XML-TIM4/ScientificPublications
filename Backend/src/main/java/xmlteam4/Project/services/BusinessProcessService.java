package xmlteam4.Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xmlteam4.Project.businessprocess.*;
import xmlteam4.Project.exceptions.EntityAlreadyExistsException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.repositories.BusinessProcessRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessProcessService {
    @Autowired
    private BusinessProcessRepository businessProcessRepository;

    public void createBusinessProcess(String scientificPaperId) throws EntityAlreadyExistsException,
            RepositoryException {
        if (businessProcessRepository.findOneById(scientificPaperId) != null)
            throw new EntityAlreadyExistsException(String.format("Business process for scientific paper with id %s " +
                    "already exists", scientificPaperId));

        TUser loggedInUser = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        businessProcessRepository.createBusinessProcess(scientificPaperId, loggedInUser.getId());
    }

    public TBusinessProcess findById(String scientificPaperId) throws RepositoryException {
        return businessProcessRepository.findOneById(scientificPaperId);
    }

    public TReviewCycle createNewReviewCycle(String scientificPaperId) {
        TUser loggedInUser = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return businessProcessRepository.createNewReviewCycle(loggedInUser.getId(), scientificPaperId);
    }

    public void updateBusinessProcess(TBusinessProcess businessProcess) throws RepositoryException {
        businessProcessRepository.updateBusinessProcess(businessProcess);
    }

    public TReviewCycle getActiveCycle(TBusinessProcess businessProcess) {
        return businessProcess.getReviewCycles().getReviewCycle()
                .get(businessProcess.getReviewCycles().getReviewCycle().size() - 1);
    }

    public TPhase getActivePhase(TReviewCycle activeCycle) {
        return activeCycle.getPhases().getPhase().get(activeCycle.getPhases().getPhase().size() - 1);
    }

    public TActorTask getTaskByDocumentType(TPhase activePhase, DocumentType documentType) {
        return activePhase.getActorTasks().getActorTask().stream()
                .filter(at -> at.getDocumentType().equals(documentType.toString())).collect(Collectors.toList()).get(0);
    }

    public TActorTask getTaskByDocumentTypeAndUserId(TPhase activePhase, DocumentType documentType, String userId) {
        return activePhase.getActorTasks().getActorTask().stream()
                .filter(at -> at.getDocumentType().equals(documentType.toString())
                        && at.getUserId().equals(userId))
                .collect(Collectors.toList())
                .get(0);
    }

    public TActorTask getTaskByDocumentTypeAndDifferentUserId(TPhase activePhase, DocumentType documentType,
                                                              String userId) {
        return activePhase.getActorTasks().getActorTask().stream()
                .filter(at -> at.getDocumentType().equals(documentType.toString())
                        && !at.getUserId().equals(userId))
                .collect(Collectors.toList())
                .get(0);
    }

    public TPhase createReviewPhase(String firstReviewer, String secondReviewer) {
        ObjectFactory factory = new ObjectFactory();
        TPhase reviewPhase = factory.createTPhase();
        reviewPhase.setTitle(PhaseTitle.REVIEW.toString());
        reviewPhase.setCanAdvance(false);

        TPhase.ActorTasks reviewTasks = factory.createTPhaseActorTasks();

        TActorTask firstReview = factory.createTActorTask();
        firstReview.setFinished(false);
        firstReview.setDocumentId("");
        firstReview.setUserId(firstReviewer);
        firstReview.setUserType(UserType.REVIEWER.toString());
        firstReview.setDocumentType(DocumentType.REVIEW.toString());

        TActorTask secondReview = factory.createTActorTask();
        secondReview.setFinished(false);
        secondReview.setDocumentId("");
        secondReview.setUserId(secondReviewer);
        secondReview.setUserType(UserType.REVIEWER.toString());
        secondReview.setDocumentType(DocumentType.REVIEW.toString());

        reviewTasks.getActorTask().add(firstReview);
        reviewTasks.getActorTask().add(secondReview);

        reviewPhase.setActorTasks(reviewTasks);

        return reviewPhase;
    }

    public List<String> getOwnReviewsIds() {
        TUser loggedInUser = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return businessProcessRepository.getOwnReviewsIds(loggedInUser.getId());
    }

    public List<String> getFinishedReviewsIds() {
        return businessProcessRepository.getFinishedReviewsIds();
    }

    public String getPaperCreatorId(String scientificPaperId) throws RepositoryException {
        return businessProcessRepository.getPaperCreatorId(scientificPaperId);
    }
}
