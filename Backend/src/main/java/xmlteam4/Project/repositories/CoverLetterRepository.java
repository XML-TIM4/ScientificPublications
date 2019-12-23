package xmlteam4.Project.repositories;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.utilities.exist.DBRetrieve;
import xmlteam4.Project.utilities.exist.DBSave;

//import static xmlteam4.Project.utilities.template.XUpdateTemplate.TARGET_NAMESPACE;


@Repository
public class CoverLetterRepository {
    @Value("${cover-letter-collection-id}")
    private String coverLetterCollectionId;

    @Value("${x-update-template.target-namespace}")
    private String TARGET_NAMESPACE;

    //public static String coverLetterXSLPath = "src/main/resources/data/xslt/CoverLetter.xsl";
    //public static String coverLetterSchemaPath = "src/main/resources/data/schemas/CoverLetter.xsd";

    public String findOne(String id) throws Exception {
        String xPathExp = String.format("//CoverLetter[@id=\"%s\"]", id);
        ResourceSet resultSet = DBRetrieve.executeXPathExpression(coverLetterCollectionId, xPathExp, TARGET_NAMESPACE);
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

    public String create(String id, String coverLetter) throws Exception {
        DBSave.store(coverLetterCollectionId, id, coverLetter);
        return id;
    }

    // functional requirements do not specify update and delete for cover letter, so it will be commented and
    // serve only as example until other repositories are created
/*
    // method should be String update(String id, String coverLetter)
    public String update(String coverLetter) throws Exception {
        DOMParser domparser = new DOMParser(); // processing should be done in service
        Document document = domparser.buildDocument(coverLetter, coverLetterSchemaPath);
        String id = document.getDocumentElement().getAttribute("id");

        String oldCoverLetterData = this.findOne(id);
        if (oldCoverLetterData == null) {
            throw new Exception("Cover letter with id: " + id);
        }
        this.delete(id);
        DBSave.store(coverLetterCollectionId, id, coverLetter);
        return id;
    }

    public void delete(String id) throws Exception {
        String xPathExp = "/coverLetter";
        long mods = DBUpdate.delete(coverLetterCollectionId, id, xPathExp);
        if (mods == 0) {
            throw new Exception(String.format("Cover letter with documentId %s", id));
        }
    }
*/
}
