package xmlteam4.Project.utilities.transformers.xsltransformer.fop;

import org.apache.fop.apps.FopFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FopFactoryFactoryConfiguration {
    @Bean
    public FopFactoryFactory fopFactoryFactory() {
        return new FopFactoryFactory();
    }

    @Bean
    public FopFactory fopFactory() throws Exception {
        return fopFactoryFactory().getObject();
    }
}
