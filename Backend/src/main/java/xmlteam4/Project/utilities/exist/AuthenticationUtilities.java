package xmlteam4.Project.utilities.exist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

import javax.annotation.PostConstruct;

@Component
public class AuthenticationUtilities {
    public static String uri;
    public static String host;
    public static int port;
    public static String user;
    public static String password;
    public static String driver;

    @Value("${exist-connection-uri}")
    private String _uri;

    @Value("${conn.host}")
    private String _host;

    @Value("${conn.exist-port}")
    private int _port;

    @Value("${conn.user}")
    private String _user;

    @Value("${conn.password}")
    private String _password;

    @Value("${conn.driver}")
    private String _driver;


    @PostConstruct
    public void init() {
        AuthenticationUtilities.uri = _uri;
        AuthenticationUtilities.driver = _driver;
        AuthenticationUtilities.password = _password;
        AuthenticationUtilities.user = _user;
        AuthenticationUtilities.host = _host;
        AuthenticationUtilities.port = _port;
    }


    public static Collection initDBCollection(String collectionId) throws ClassNotFoundException, IllegalAccessException, InstantiationException, XMLDBException {
        Collection col;
        Class<?> cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        col = DatabaseManager.getCollection(uri + collectionId, user, password);
        col.setProperty("indent", "yes");
        return col;
    }
}
