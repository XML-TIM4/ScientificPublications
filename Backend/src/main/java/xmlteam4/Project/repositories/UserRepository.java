package xmlteam4.Project.repositories;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.model.Users;
import xmlteam4.Project.utilities.exist.CRUDService;
import xmlteam4.Project.utilities.exist.QueryService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Repository
public class UserRepository {
    @Value("${user-collection-id}")
    private String userCollectionId;

    @Autowired
    private CRUDService crudService;

    @Autowired
    private QueryService queryService;


    public TUser findOneByEmail(String email) throws RepositoryException {
        String xPathExp = String.format("//user[email='%s']", email);
        try {
            ResourceSet resultSet = queryService.executeXPathQuery(userCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            ResourceIterator i = resultSet.getIterator();
            XMLResource res = null;
            TUser retVal = null;

            if (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
                retVal = unmarshallUser(res.getContentAsDOM());
            }

            if (res != null)
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException exception) {
                    exception.printStackTrace();
                }

            return retVal;
        } catch (XMLDBException | JAXBException e) {
            throw new RepositoryException("Failed to find user");
        }
    }

    public TUser save(TUser user) throws RepositoryException {
        try {
            String userXml = marshallUser(user);

            Collection col = crudService.getOrCreateCollection(userCollectionId, 0);

            queryService.append(col, userCollectionId, "/users", userXml);

            return user;
        } catch (XMLDBException | JAXBException e) {
            throw new RepositoryException("Failed to save user");
        }
    }

    private TUser unmarshallUser(Node node) throws JAXBException {
        TUser user;

        JAXBContext context = JAXBContext.newInstance(TUser.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        user = (TUser) unmarshaller.unmarshal(node);

        return user;
    }

    private String marshallUser(TUser user) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TUser.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        marshaller.marshal(user, stream);

        String userXML = new String(stream.toByteArray());

        return userXML.substring(userXML.indexOf('\n') + 1);
    }

    public String marshallAll(Users users) throws JAXBException {
        OutputStream os = new ByteArrayOutputStream();
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(users, os);

        return os.toString();
    }
}
