package xmlteam4.Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.model.ObjectFactory;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.model.Users;
import xmlteam4.Project.repositories.UserRepository;
import xmlteam4.Project.utilities.exist.CRUDService;
import xmlteam4.Project.utilities.exist.ExistAuthenticationUtilities;
import xmlteam4.Project.utilities.idgenerator.IDGenerator;

import javax.xml.bind.JAXBException;

@Component
public class StartupService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CRUDService crudService;

    @Autowired
    private IDGenerator idGenerator;

    @Value("${user-collection-id}")
    private String userCollectionId;


    @EventListener(ApplicationReadyEvent.class)
    public void doOnStartup() {
        initializeDatabase();
        initializeUsersIfNone();
    }

    private void initializeDatabase() {
        try {
            Class<?> cl = Class.forName(ExistAuthenticationUtilities.driver);
            Database db = (Database) cl.newInstance();
            db.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(db);
        } catch (IllegalAccessException | InstantiationException | XMLDBException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeUsersIfNone() {
        try {
            Collection col = crudService.getOrCreateCollection(userCollectionId, 0);
            XMLResource resource = (XMLResource) col.getResource("users.xml");

            if (resource == null) {
                ObjectFactory factory = new ObjectFactory();
                Users users = factory.createUsers();
                TUser user = new TUser();
                user.setEditor(true);
                user.setId(idGenerator.createID());
                user.setPassword(passwordEncoder.encode("adminadmin"));
                user.setEmail("admin@admin.com");
                user.setExpertise("");
                users.getUser().add(user);
                String usersXML = userRepository.marshallAll(users);
                resource = (XMLResource) col.createResource("users.xml", XMLResource.RESOURCE_TYPE);
                resource.setContent(usersXML);
                col.storeResource(resource);
            }
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
