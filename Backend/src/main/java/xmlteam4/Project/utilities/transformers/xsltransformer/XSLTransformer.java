package xmlteam4.Project.utilities.transformers.xsltransformer;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.tomcat.websocket.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmlteam4.Project.exceptions.TransformationException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class XSLTransformer {
    @Autowired
    private FopFactory fopFactory;

    @Autowired
    private TransformerFactory transformerFactory;

    public String generateHTML(String source, String xsltTemplatePath) throws TransformationException {
        try {
            File tf = new File(xsltTemplatePath); // template file
            StringWriter out = new StringWriter(); // result
            StringReader src = new StringReader(source); // source string

            Transformer t = transformerFactory.newTransformer(new StreamSource(tf));

            Source s = new StreamSource(src);
            Result r = new StreamResult(out);
            t.transform(s, r);
            return out.toString();
        } catch (TransformerException e) {
            throw new TransformationException("Failed to transform document");
        }
    }

    public ByteArrayOutputStream generatePDF(String sourceStr, String xslt_fo_TemplatePath) throws Exception {

        // xslt_fo_TemplatePath = XSL_FILE2;
        // File ssourceStr = new File(INPUT_FILE2);

        // Point to the XSL-FO file
        File xslFile = new File(xslt_fo_TemplatePath);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject
        StreamSource source = new StreamSource(new StringReader(sourceStr));
        // StreamSource source = new StreamSource(ssourceStr);

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        return outStream;
    }

}
