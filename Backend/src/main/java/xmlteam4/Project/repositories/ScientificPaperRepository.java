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
public class ScientificPaperRepository {
	@Value("${scientific-paper-collection-id}")
    private String scientificPaperCollectionId;

    public String findOne(String id) throws Exception {
        String xPathExp = String.format("//scientific-paper[@id='%s']", id);
        ResourceSet resultSet = DBRetrieve.executeXPathExpression(scientificPaperCollectionId, xPathExp, TARGET_NAMESPACE);
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

    public String create(String id, String scientificPaper) throws Exception {
        DBSave.store(scientificPaperCollectionId, id, scientificPaper);
        return id;
    }

    public String update(String id, String scientificPaper) throws Exception {
        String oldScientificPaperData = this.findOne(id);
        if (oldScientificPaperData == null) {
            throw new Exception("Scientific Paper with id: " + id);
        }
        this.delete(id);
        DBSave.store(scientificPaperCollectionId, id, scientificPaper);
        return id;
    }

    public void delete(String id) throws Exception {
        String xPathExp = "/scientificPaper";
        long mods = DBUpdate.delete(scientificPaperCollectionId, id, xPathExp);
        if (mods == 0) {
            throw new Exception(String.format("Scientific Paper with documentId %s", id));
        }
    }
}
