package xmlteam4.Project.utilities.exist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

import javax.annotation.PostConstruct;

@Component
public class ExistAuthenticationUtilities {
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
        ExistAuthenticationUtilities.uri = _uri;
        ExistAuthenticationUtilities.driver = _driver;
        ExistAuthenticationUtilities.password = _password;
        ExistAuthenticationUtilities.user = _user;
        ExistAuthenticationUtilities.host = _host;
        ExistAuthenticationUtilities.port = _port;
    }
}
