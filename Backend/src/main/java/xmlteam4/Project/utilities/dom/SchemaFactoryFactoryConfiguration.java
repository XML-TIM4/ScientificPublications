package xmlteam4.Project.utilities.dom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.validation.SchemaFactory;

@Configuration
public class SchemaFactoryFactoryConfiguration {
    @Bean
    public SchemaFactoryFactory schemaFactoryFactory() {
        return new SchemaFactoryFactory();
    }

    @Bean
    public SchemaFactory schemaFactory() {
        return schemaFactoryFactory().getObject();
    }
}
