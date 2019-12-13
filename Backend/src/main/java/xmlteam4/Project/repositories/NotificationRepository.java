package xmlteam4.Project.repositories;

import static xmlteam4.Project.utilities.template.XUpdateTemplate.TARGET_NAMESPACE;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import xmlteam4.Project.utilities.exist.DBRetrieve;
import xmlteam4.Project.utilities.exist.DBSave;
import xmlteam4.Project.utilities.exist.DBUpdate;


@Repository
public class NotificationRepository {
	@Value("${notification-collection-id}")
    private String notificationCollectionId;

    public String findOne(String id) throws Exception {
        String xPathExp = String.format("//notification[@id='%s']", id);
        ResourceSet resultSet = DBRetrieve.executeXPathExpression(notificationCollectionId, xPathExp, TARGET_NAMESPACE);
        if (resultSet == null)
            return null;

        ResourceIterator i = resultSet.getIterator();
        XMLResource res = null;
        String retVal = null;

        if (i.hasMoreResources()) {
            res = (XMLResource) i.nextResource();
            retVal = res.getContent().toString();
        }

        if (res != null)
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException exception) {
                exception.printStackTrace();
            }

        return retVal;
    }

    public String create(String id, String notification) throws Exception {
        DBSave.store(notificationCollectionId, id, notification);
        return id;
    }

    public String update(String id, String notification) throws Exception {
        String oldNotificationData = this.findOne(id);
        if (oldNotificationData == null) {
            throw new Exception("Notification with id: " + id);
        }
        this.delete(id);
        DBSave.store(notificationCollectionId, id, notification);
        return id;
    }

    public void delete(String id) throws Exception {
        String xPathExp = "/notification";
        long mods = DBUpdate.delete(notificationCollectionId, id, xPathExp);
        if (mods == 0) {
            throw new Exception(String.format("Notification with documentId %s", id));
        }
    }
}
