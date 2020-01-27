package xmlteam4.Project.utilities.sparql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FusekiAuthenticationUtilities {
    public static String endpoint;
    public static String queryEndpoint;
    public static String updateEndpoint;
    public static String dataEndpoint;

    @Value("${conn.fuseki-endpoint}")
    private String _endpoint;

    @Value("${conn.query}")
    private String _query;

    @Value("${conn.update}")
    private String _update;

    @Value("${conn.data}")
    private String _data;

    @PostConstruct
    public void init() {
        FusekiAuthenticationUtilities.endpoint = _endpoint;
        FusekiAuthenticationUtilities.dataEndpoint = String.join("/", _endpoint, _data);
        FusekiAuthenticationUtilities.queryEndpoint = String.join("/", _endpoint, _query);
        FusekiAuthenticationUtilities.updateEndpoint = String.join("/", _endpoint, _update);
    }
}
