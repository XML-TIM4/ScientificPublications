package xmlteam4.Project.utilities.exist;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.exceptions.CRUDServiceException;

import javax.xml.transform.OutputKeys;

@Component
public class CRUDService {
    public void storeDocument(String collectionName, String documentId, String xmlEntity)
            throws CRUDServiceException {
        Collection col = null;
        XMLResource res = null;

        try {
            col = getOrCreateCollection(collectionName, 0);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(xmlEntity);
            col.storeResource(res);
        } catch (XMLDBException ex) {
            throw new CRUDServiceException("Failed to store document in database");
        } finally {
            freeResources(col, res);
        }
    }

    public void deleteDocument(String collectionName, String documentId) throws CRUDServiceException {
        Collection col = null;
        XMLResource resource = null;

        try {
            resource = loadDocument(collectionName, documentId);
            col = getOrCreateCollection(collectionName, 0);
            col.removeResource(resource);
        } catch (CRUDServiceException | XMLDBException e) {
            throw new CRUDServiceException("Failed to delete document from database");
        } finally {
            freeResources(col, resource);
        }

    }

    public XMLResource loadDocument(String collectionName, String documentId)
            throws CRUDServiceException {
        Collection col = null;
        XMLResource res = null;

        try {
            col = DatabaseManager.getCollection(ExistAuthenticationUtilities.uri + collectionName);
            col.setProperty(OutputKeys.INDENT, "yes");
            res = (XMLResource) col.getResource(documentId);

            if (res == null)
                throw new Exception();
        } catch (Exception ex) {
            throw new CRUDServiceException("Failed to load document from database");
        } finally {
            freeResources(col, res);
        }

        return res;
    }

    public Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset)
            throws XMLDBException {
        Collection col = DatabaseManager.getCollection(ExistAuthenticationUtilities.uri + collectionUri,
                ExistAuthenticationUtilities.user,
                ExistAuthenticationUtilities.password);

        if (col == null) {

            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String[] pathSegments = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/").append(pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(ExistAuthenticationUtilities.uri + path,
                        ExistAuthenticationUtilities.user,
                        ExistAuthenticationUtilities.password);

                if (startCol == null) {
                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(ExistAuthenticationUtilities.uri + parentPath,
                            ExistAuthenticationUtilities.user,
                            ExistAuthenticationUtilities.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol
                            .getService("CollectionManagementService", "1.0");

                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

    private void freeResources(Collection col, XMLResource res) {
        if (res != null) {
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }

        if (col != null) {
            try {
                col.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }
}
