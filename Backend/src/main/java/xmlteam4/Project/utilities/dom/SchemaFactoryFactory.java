package xmlteam4.Project.utilities.dom;

import org.springframework.beans.factory.FactoryBean;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;

public class SchemaFactoryFactory implements FactoryBean<SchemaFactory> {
    @Override
    public SchemaFactory getObject() {
        return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    }

    @Override
    public Class<?> getObjectType() {
        return SchemaFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
