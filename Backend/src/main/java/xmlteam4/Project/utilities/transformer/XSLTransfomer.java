package xmlteam4.Project.utilities.transformer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.xalan.xsltc.trax.TransformerFactoryImpl;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
public class XSLTransfomer {

    private TransformerFactory transformerFactory;

    public XSLTransfomer(){

        transformerFactory = new TransformerFactoryImpl();
    }

    public String generateHTML(String source, String xsltTemplatePath) throws Exception
    {

        File tf = new File(xsltTemplatePath); // template file
        StringWriter out = new StringWriter(); // result
        StringReader src = new StringReader(source); // source string

        Transformer t = transformerFactory.newTransformer(new StreamSource(tf));

        Source s = new StreamSource(src);
        Result r = new StreamResult(out);
        t.transform(s,r);
        return out.toString();
    }



}