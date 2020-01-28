package xmlteam4.Project.repositories;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.exceptions.CRUDServiceException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.utilities.exist.CRUDService;
import xmlteam4.Project.utilities.exist.QueryService;


@Repository
public class ReviewRepository {
    @Value("${review-collection-id}")
    private String reviewCollectionId;

    @Autowired
    private CRUDService crudService;

    @Autowired
    private QueryService queryService;

    public String findOne(String id) throws RepositoryException {
        String xPathExp = String.format("//review[@id='%s']", id);
        try {
            ResourceSet resultSet = queryService.executeXPathQuery(reviewCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            XMLResource res = queryService.extractSingleResource(resultSet);
            String retVal = res.getContent().toString();
            ((EXistResource) res).freeResources();
            return retVal;
        } catch (XMLDBException e) {
            throw new RepositoryException("Failed to find review");
        }
    }

    public String create(String id, String review) throws RepositoryException {
        try {
            crudService.storeDocument(reviewCollectionId, id, review);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to create review");
        }
        return id;
    }

    public String update(String id, String review) throws RepositoryException {
        String oldReviewData = findOne(id);

        if (oldReviewData == null) {
            throw new RepositoryException("Failed to update nonexistent review");
        }

        delete(id);

        try {
            crudService.storeDocument(reviewCollectionId, id, review);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to update review");
        }
        return id;
    }

    public void delete(String id) throws RepositoryException {
        try {
            crudService.deleteDocument(reviewCollectionId, id);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to delete review");
        }
    }
}
