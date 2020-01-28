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
public class ScientificPaperRepository {
    @Value("${scientific-paper-collection-id}")
    private String scientificPaperCollectionId;

    @Autowired
    private CRUDService crudService;

    @Autowired
    private QueryService queryService;


    public String findOne(String id) throws RepositoryException {
        String xPathExp = String.format("//scientific-paper[@id='%s']", id);

        try {
            ResourceSet resultSet = queryService.executeXPathQuery(scientificPaperCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            XMLResource res = queryService.extractSingleResource(resultSet);
            String retVal = res.getContent().toString();
            ((EXistResource) res).freeResources();
            return retVal;
        } catch (XMLDBException e) {
            throw new RepositoryException("Failed to find scientific paper");
        }
    }

    public String create(String id, String scientificPaper) throws RepositoryException {
        try {
            crudService.storeDocument(scientificPaperCollectionId, id, scientificPaper);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to create scientific paper");
        }
        return id;
    }

    public String update(String id, String scientificPaper) throws RepositoryException {
        String oldScientificPaperData = findOne(id);

        if (oldScientificPaperData == null) {
            throw new RepositoryException("Failed to update nonexistent scientific paper");
        }

        delete(id);
        try {
            crudService.storeDocument(scientificPaperCollectionId, id, scientificPaper);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to update scientific paper");
        }
        return id;
    }

    public void delete(String id) throws RepositoryException {
        try {
            crudService.deleteDocument(scientificPaperCollectionId, id);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to delete scientific paper");
        }
    }
}
