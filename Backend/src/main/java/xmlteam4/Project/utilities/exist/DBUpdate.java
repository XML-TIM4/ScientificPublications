package xmlteam4.Project.utilities.exist;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;

import org.xmldb.api.base.Resource;
import org.xmldb.api.modules.XUpdateQueryService;

public class DBUpdate {

    public static long update(String collectionId, String documentId, String contextXPath, String xmlFragment, String updateTemplate) throws Exception {
        long mods = 0;
        Collection col = ExistAuthenticationUtilities.initDBCollection(collectionId);
        try {
            XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            String command = String.format(updateTemplate, contextXPath, xmlFragment);

            System.out.println(command);

            mods = xupdateService.updateResource(documentId, command);
            System.out.println("[INFO] " + mods + " modifications processed.");
            // long mods = xupdateService.updateResource(,String.format(INSERT_AFTER, contextXPath, xmlFragment));
            return mods;
        } finally {
            try {
                col.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }

    public static long delete(String collectionId, String documentId, String contextXPath) throws Exception {
        long mods = 0;
        Collection col = ExistAuthenticationUtilities.initDBCollection(collectionId);
        try {
            Resource res = col.getResource(documentId);
            col.removeResource(res);
            mods = 1;
            return mods;
        } finally {
            try {
                col.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }
}
