package xmlteam4.Project.utilities.transformer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.transform.TransformerFactory;

@Configuration
public class TransformerFactoryFactoryConfiguration {
    @Bean
    public TransformerFactoryFactory transformerFactoryFactory() {
        return new TransformerFactoryFactory();
    }

    @Bean
    public TransformerFactory transformerFactory() {
        return transformerFactoryFactory().getObject();
    }
}
