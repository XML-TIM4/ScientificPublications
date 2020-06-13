package xmlteam4.Project.utilities.exist;

import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

import java.util.ArrayList;
import java.util.List;

import static xmlteam4.Project.utilities.exist.XUpdateTemplate.*;

@Component
public class QueryService {
    public ResourceSet executeXPathQuery(String collectionNameInDb, String xPathQuery) throws XMLDBException {
        ResourceSet result;

        // driver initialization
        System.out.println("[INFO] Loading driver class: " + ExistAuthenticationUtilities.driver);

        Class<?> cl;
        Database db;

        try {
            cl = Class.forName(ExistAuthenticationUtilities.driver);
            db = (Database) cl.newInstance();
            db.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(db);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Collection collection = null;

        try {
            System.out.println("[INFO] Retrieving the collection: " + collectionNameInDb);
            collection = DatabaseManager.getCollection(ExistAuthenticationUtilities.uri + collectionNameInDb);

            if (collection == null) {
                return null;
            }

            XPathQueryService xPathService = createXPathQueryService(collection);
            System.out.println("[INFO] Invoking XPath query service for following query: " + xPathQuery);
            result = xPathService.query(xPathQuery);
            System.out.println("[INFO] Computing the results... ");
        } finally {
            if (collection != null) {
                try {
                    collection.close();
                } catch (XMLDBException xmldbe) {
                    xmldbe.printStackTrace();
                }
            }
        }
        return result;
    }

    public void insertAfter(Collection col, String documentId, String contextXPath, String xmlFragment) {
        try {
            XUpdateQueryService xUpdateService = createXUpdateQueryService(col);
            xUpdateService.updateResource(documentId, String.format(INSERT_AFTER, contextXPath, xmlFragment));
        } catch (XMLDBException e) {
            e.printStackTrace();
        }
    }

    public void insertBefore(Collection col, String documentId, String contextXPath, String xmlFragment) {
        try {
            XUpdateQueryService xUpdateService = createXUpdateQueryService(col);
            xUpdateService.updateResource(documentId, String.format(INSERT_BEFORE, contextXPath, xmlFragment));
        } catch (XMLDBException e) {
            e.printStackTrace();
        }
    }

    public void append(Collection col, String documentId, String contextXPath, String xmlFragment) {
        try {
            XUpdateQueryService xUpdateService = createXUpdateQueryService(col);
            xUpdateService.updateResource(documentId, String.format(APPEND, contextXPath, xmlFragment));
        } catch (XMLDBException e) {
            e.printStackTrace();
        }
    }

    public void update(Collection col, String documentId, String contextXPath, String xmlFragment) {
        try {
            XUpdateQueryService xUpdateService = createXUpdateQueryService(col);
            xUpdateService.updateResource(documentId, String.format(UPDATE, contextXPath, xmlFragment));
        } catch (XMLDBException e) {
            e.printStackTrace();
        }
    }

    public void remove(Collection col, String documentId, String contextXPath) {
        try {
            XUpdateQueryService xUpdateService = createXUpdateQueryService(col);
            xUpdateService.updateResource(documentId, String.format(REMOVE, contextXPath));
        } catch (XMLDBException e) {
            e.printStackTrace();
        }
    }

    public XMLResource extractSingleResource(ResourceSet resourceSet) {
        if (resourceSet == null)
            return null;

        try {
            ResourceIterator i = resourceSet.getIterator();
            XMLResource res = null;

            if (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
            }
            return res;
        } catch (XMLDBException e) {
            return null;
        }
    }

    public List<XMLResource> extractAllResources(ResourceSet resourceSet) {
        if (resourceSet == null)
            return null;

        try {
            ResourceIterator i = resourceSet.getIterator();
            List<XMLResource> resources = new ArrayList<>();

            while (i.hasMoreResources()) {
                resources.add((XMLResource) i.nextResource());
            }
            return resources;
        } catch (XMLDBException e) {
            return null;
        }
    }

    private XPathQueryService createXPathQueryService(Collection col) throws XMLDBException {
        XPathQueryService xPathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        xPathService.setProperty("indent", "yes");
        xPathService.setNamespace("", TARGET_NAMESPACE);

        return xPathService;
    }

    private XUpdateQueryService createXUpdateQueryService(Collection col) throws XMLDBException {
        col.setProperty("indent", "yes");
        XUpdateQueryService xUpdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
        xUpdateService.setProperty("indent", "yes");

        return xUpdateService;
    }
}
