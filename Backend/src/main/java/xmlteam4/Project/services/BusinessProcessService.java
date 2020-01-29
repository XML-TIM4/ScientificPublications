package xmlteam4.Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import xmlteam4.Project.businessprocess.TBusinessProcess;
import xmlteam4.Project.businessprocess.TReviewCycle;
import xmlteam4.Project.exceptions.EntityAlreadyExistsException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.repositories.BusinessProcessRepository;

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
}
