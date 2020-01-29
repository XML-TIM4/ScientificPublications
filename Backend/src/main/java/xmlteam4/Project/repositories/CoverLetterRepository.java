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
public class CoverLetterRepository {
    @Value("${cover-letter-collection-id}")
    private String coverLetterCollectionId;

    @Autowired
    private CRUDService crudService;

    @Autowired
    private QueryService queryService;

    public String findOne(String id) throws RepositoryException {
        String xPathExp = String.format("//cover-letter[@id=\"%s\"]", id);
        try {
            ResourceSet resultSet = queryService.executeXPathQuery(coverLetterCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            XMLResource res = queryService.extractSingleResource(resultSet);
            String retVal = res.getContent().toString();
            ((EXistResource) res).freeResources();
            return retVal;
        } catch (XMLDBException e) {
            throw new RepositoryException("Failed to find cover letter");
        }
    }

    public String create(String id, String coverLetter) throws RepositoryException {
        try {
            crudService.storeDocument(coverLetterCollectionId, id, coverLetter);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to create cover letter");
        }
        return id;
    }

    public String update(String id, String covererLetter) throws RepositoryException {
        String oldCoverLetterData = findOne(id);

        if (oldCoverLetterData == null) {
            throw new RepositoryException("Failed to update nonexistent cover letter");
        }

        delete(id);
        try {
            crudService.storeDocument(coverLetterCollectionId, id, covererLetter);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to update cover letter");
        }
        return id;
    }

    public void delete(String id) throws RepositoryException {
        try {
            crudService.deleteDocument(coverLetterCollectionId, id);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to delete cover letter");
        }
    }
}
