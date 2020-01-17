package xmlteam4.Project.utilities.exist;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class DBRetrieve {

    /**
     * conn XML DB connection properties args[0] Should be the collection ID to
     * access
     */
    public static ResourceSet executeXPathExpression(String collectionId, String xpathExp, String TARGET_NAMESPACE) throws Exception {
        ResourceSet result;

        Class<?> cl = Class.forName(AuthenticationUtilities.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;

        try {
            // get the collection
            col = DatabaseManager.getCollection(AuthenticationUtilities.uri + collectionId);

            if (col == null) {
                return null;
            }
            // get an instance of xpath query service
            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");

            // make the service aware of namespaces, using the default one
            xpathService.setNamespace("", TARGET_NAMESPACE);
            // execute xpath expression
            result = xpathService.query(xpathExp);
            // handle the results
        } finally {

            // don't forget to cleanup
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return result;
    }
}