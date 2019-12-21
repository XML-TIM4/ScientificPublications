package xmlteam4.Project.repositories;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.utilities.exist.DBRetrieve;
import xmlteam4.Project.utilities.exist.DBUpdate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

import static xmlteam4.Project.utilities.template.XUpdateTemplate.*;

@Repository
public class UserRepository {
    @Value("${user-collection-id}")
    private String userCollectionId;

    public TUser findOneByUsername(String username) throws RepositoryException {
        try {
            String xPathExp = String.format("users/user[username='%s']", username);
            ResourceSet resultSet = DBRetrieve.executeXPathExpression(userCollectionId, xPathExp, TARGET_NAMESPACE);

            if (resultSet == null)
                return null;

            ResourceIterator i = resultSet.getIterator();
            XMLResource res = null;
            TUser retVal = null;

            if (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
                retVal = (TUser) res.getContent();
            }

            if (res != null)
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException exception) {
                    exception.printStackTrace();
                }

            return retVal;
        } catch (Exception e) {
            throw new RepositoryException("Error in repository");
        }
    }

    public TUser save(TUser user) throws RepositoryException {
        try {
            if (findOneByUsername(user.getUsername()) != null)
                throw new RepositoryException("User already exists");

            String xmlElement = marshallUser(user);

            if (DBUpdate.update(userCollectionId, "users", "users", xmlElement, INSERT_AFTER) == 0L)
                throw new RepositoryException("Failed to save user");
            else
                return findOneByUsername(user.getUsername());
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public TUser update(TUser user) throws RepositoryException {
        try {
            if (findOneByUsername(user.getUsername()) == null)
                throw new RepositoryException("User does not exist");

            String xmlElement = marshallUser(user);

            if (DBUpdate.update(userCollectionId, "users", "users", xmlElement, UPDATE) == 0L)
                throw new RepositoryException("Failed to update user");
            else
                return findOneByUsername(user.getUsername());
        } catch (Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private String marshallUser(TUser user) throws Exception {
        JAXBContext context = JAXBContext.newInstance(TUser.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        marshaller.marshal(user, stream);

        return new String(stream.toByteArray());
    }

}
