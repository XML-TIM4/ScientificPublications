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
public class NotificationRepository {
    @Value("${notification-collection-id}")
    private String notificationCollectionId;

    @Autowired
    private CRUDService crudService;

    @Autowired
    private QueryService queryService;

    public String findOne(String id) throws RepositoryException {
        String xPathExp = String.format("//notification[@id='%s']", id);

        try {
            ResourceSet resultSet = queryService.executeXPathQuery(notificationCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            XMLResource res = queryService.extractSingleResource(resultSet);
            String retVal = res.getContent().toString();
            ((EXistResource) res).freeResources();
            return retVal;
        } catch (XMLDBException e) {
            throw new RepositoryException("Failed to find notification");
        }
    }

    public String create(String id, String notification) throws RepositoryException {
        try {
            crudService.storeDocument(notificationCollectionId, id, notification);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to create notification");
        }
        return id;
    }
}
