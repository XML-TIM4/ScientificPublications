package xmlteam4.Project.utilities.transformers.xsltransformer.fop;

import org.springframework.beans.factory.FactoryBean;
import org.apache.fop.apps.FopFactory;

import java.io.File;

public class FopFactoryFactory implements FactoryBean<FopFactory> {
    @Override
    public FopFactory getObject() throws Exception {
        return FopFactory.newInstance(new File("src/main/resources/fop.xconf"));
    }

    @Override
    public Class<?> getObjectType() {
        return FopFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
