package xmlteam4.Project.utilities.transformers.xsltransformer;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmlteam4.Project.exceptions.TransformationException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class XSLTransformer {
    @Autowired
    private FopFactory fopFactory;

    @Autowired
    private TransformerFactory transformerFactory;

    public String generateXML(String source, String xsltTemplatePath) throws TransformationException {
        try {
            File tf = new File(xsltTemplatePath);
            StringWriter out = new StringWriter();
            StringReader src = new StringReader(source);

            Transformer t = transformerFactory.newTransformer(new StreamSource(tf));

            Source s = new StreamSource(src);
            Result r = new StreamResult(out);
            t.transform(s, r);
            return out.toString();
        } catch (TransformerException e) {
            throw new TransformationException("Failed to generate XML");
        }
    }

    public String mergeReviews(String source, String updates, String xsltTemplatePath) throws TransformationException {
        try {
            File tf = new File(xsltTemplatePath);
            StringWriter out = new StringWriter();
            StringReader src = new StringReader(source);

            Transformer t = transformerFactory.newTransformer(new StreamSource(tf));

            String fileName = "data/temp/temp.xml";
            FileWriter fw = new FileWriter(fileName);
            fw.write(updates);
            fw.close();

            Source s = new StreamSource(src);
            Result r = new StreamResult(out);
            t.setParameter("fileName", fileName);
            t.transform(s, r);
            return out.toString();
        } catch (TransformerException | IOException e) {
            throw new TransformationException("Failed to merge reviews");
        }
    }

    public ByteArrayOutputStream generatePDF(String sourceStr, String xslt_fo_TemplatePath) throws TransformationException {
        try {
            File xslFile = new File(xslt_fo_TemplatePath);

            StreamSource transformSource = new StreamSource(xslFile);

            StreamSource source = new StreamSource(new StringReader(sourceStr));

            FOUserAgent userAgent = fopFactory.newFOUserAgent();

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

            Result res = new SAXResult(fop.getDefaultHandler());

            xslFoTransformer.transform(source, res);

            return outStream;
        } catch (Exception e) {
            throw new TransformationException("Failed to generate PDF");
        }
    }

}
