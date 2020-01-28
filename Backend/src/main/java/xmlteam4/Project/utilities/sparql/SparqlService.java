package xmlteam4.Project.utilities.sparql;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Component
public class SparqlService {

    public void createGraph(String graphName, String rdf) {
        Model model = ModelFactory.createDefaultModel();

        model.read(new ByteArrayInputStream(rdf.getBytes()), null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);

        String query = SparqlUtil
                .insertData(FusekiAuthenticationUtilities.endpoint + graphName, new String(out.toByteArray()));

        UpdateRequest request = UpdateFactory.create(query);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request,
                FusekiAuthenticationUtilities.updateEndpoint);

        processor.execute();
    }

    public void deleteGraph(String graphName) {
        String query = SparqlUtil.dropGraph(FusekiAuthenticationUtilities.endpoint + graphName);
        UpdateRequest request = UpdateFactory.create(query);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request,
                FusekiAuthenticationUtilities.queryEndpoint);

        processor.execute();
    }

    public void queryGraph(String graphName, String condition) {
        String query = SparqlUtil.selectData(FusekiAuthenticationUtilities.endpoint + graphName, condition);
        QueryExecution execution = QueryExecutionFactory.sparqlService(FusekiAuthenticationUtilities.queryEndpoint,
                query);

        ResultSet results = execution.execSelect();
        // TODO
    }
}