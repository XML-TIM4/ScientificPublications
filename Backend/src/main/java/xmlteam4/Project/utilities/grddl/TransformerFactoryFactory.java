package xmlteam4.Project.utilities.grddl;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.springframework.beans.factory.FactoryBean;

import javax.xml.transform.TransformerFactory;

public class TransformerFactoryFactory implements FactoryBean<TransformerFactory> {
    @Override
    public TransformerFactory getObject() {
        return new TransformerFactoryImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return TransformerFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
