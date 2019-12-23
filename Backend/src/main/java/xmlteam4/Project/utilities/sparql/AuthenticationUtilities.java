package xmlteam4.Project.utilities.sparql;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class AuthenticationUtilities {
    public static String endpoint;
    public static String dataset;
    public static String queryEndpoint;
    public static String updateEndpoint;
    public static String dataEndpoint;

    @Value("${conn.fuseki-endpoint}")
    private String _endpoint;

    @Value("${conn.dataset}")
    private String _dataset;

    @Value("${conn.query}")
    private String _query;

    @Value("${conn.update}")
    private String _update;

    @Value("${conn.data}")
    private String _data;

    @PostConstruct
    public void init() {
        AuthenticationUtilities.dataEndpoint = _data;
        AuthenticationUtilities.dataset = _dataset;
        AuthenticationUtilities.endpoint = _endpoint;
        AuthenticationUtilities.queryEndpoint = _query;
        AuthenticationUtilities.updateEndpoint = _update;
    }
}
