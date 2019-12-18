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
public class ReviewRepository {
	@Value("${review-collection-id}")
    private String reviewCollectionId;

    public String findOne(String id) throws Exception {
        String xPathExp = String.format("//review[@id='%s']", id);
        ResourceSet resultSet = DBRetrieve.executeXPathExpression(reviewCollectionId, xPathExp, TARGET_NAMESPACE);
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

    public String create(String id, String review) throws Exception {
        DBSave.store(reviewCollectionId, id, review);
        return id;
    }

    public String update(String id, String review) throws Exception {
        String oldReviewData = this.findOne(id);
        if (oldReviewData == null) {
            throw new Exception("Review with id: " + id);
        }
        this.delete(id);
        DBSave.store(reviewCollectionId, id, review);
        return id;
    }

    public void delete(String id) throws Exception {
        String xPathExp = "/review";
        long mods = DBUpdate.delete(reviewCollectionId, id, xPathExp);
        if (mods == 0) {
            throw new Exception(String.format("Review with documentId %s", id));
        }
    }
}
