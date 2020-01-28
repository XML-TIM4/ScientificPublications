package xmlteam4.Project.utilities.dom.dbf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.DocumentBuilderFactory;

@Configuration
public class DocumentBuilderFactoryFactoryConfiguration {
    @Bean
    public DocumentBuilderFactoryFactory documentBuilderFactoryFactory() {
        return new DocumentBuilderFactoryFactory();
    }

    @Bean
    public DocumentBuilderFactory documentBuilderFactory() {
        return documentBuilderFactoryFactory().getObject();
    }
}
