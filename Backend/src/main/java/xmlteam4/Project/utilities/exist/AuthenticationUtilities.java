package xmlteam4.Project.utilities.exist;

import org.springframework.beans.factory.annotation.Value;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

public class AuthenticationUtilities {
    @Value("${exist-connection-uri}")
    public static String uri;

    @Value("${conn.host}")
    public static String host;

    @Value("${conn.port}")
    public static int port;

    @Value("${conn.user}")
    public static String user;

    @Value("${conn.password}")
    public static String password;

    @Value("${conn.driver}")
    public static String driver;


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