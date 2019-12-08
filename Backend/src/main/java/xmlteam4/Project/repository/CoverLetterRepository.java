package xmlteam4.Project.repository;

import static xmlteam4.Project.utilities.template.XUpdateTemplate.TARGET_NAMESPACE;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.utilities.DBRetrive;
import xmlteam4.Project.utilities.DBSave;
import xmlteam4.Project.utilities.DBUpdate;
import xmlteam4.Project.utilities.dom.DOMParser;


@Repository
public class CoverLetterRepository {

    public static String coverLetterCollectionId = "/db/sample/coverLetter";
    public static String coverLetterSchemaPath = "/data/schemas/CoverLetter.xsd";

    public String findOne(String id) throws Exception {
    	
        String retVal =  null;
        String xpathExp = "//coverLetter[@id=\"" + id + "\"]";
        ResourceSet resultSet = DBRetrive.executeXPathExpression(coverLetterCollectionId, xpathExp, TARGET_NAMESPACE);
        if(resultSet == null)
        {
            return retVal;
        }
        
        ResourceIterator i = resultSet.getIterator();
        XMLResource res = null;
        
        while(i.hasMoreResources()) {
            try {
                res = (XMLResource)i.nextResource();
                retVal =  res.getContent().toString();
                return retVal;
            } finally {
                // don't forget to cleanup resources
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return null;
    }

    public String create(String coverLetter) throws Exception {
        DOMParser domparser = new DOMParser();
        Document document = domparser.buildDocument(coverLetter,coverLetterSchemaPath);
        String id = document.getDocumentElement().getAttribute("id");
        DBSave.store(coverLetterCollectionId, id, coverLetter);
        return id;
    }

    public String update(String coverLetter) throws Exception {
        DOMParser domparser = new DOMParser();
        Document document = domparser.buildDocument(coverLetter, coverLetterSchemaPath);
        String id = document.getDocumentElement().getAttribute("id");

        String oldCoverLetterData = this.findOne(id);
        if(oldCoverLetterData == null)
        {
            throw new Exception("Cover letter with id: " + id);
        }
        this.delete(id);
        DBSave.store(coverLetterCollectionId, id, coverLetter);
        return id;
    }

    public void delete(String id) throws Exception {
        String xpathExp = "/coverLetter";
        long mods = DBUpdate.delete(coverLetterCollectionId, id, xpathExp);
        if(mods == 0)
        {
            throw new Exception(String.format("Cover letter with documentId %s", id));
        }
    }

}
