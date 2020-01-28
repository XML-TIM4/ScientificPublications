package xmlteam4.Project.utilities.dom.dbf;

import org.springframework.beans.factory.FactoryBean;

import javax.xml.parsers.DocumentBuilderFactory;

public class DocumentBuilderFactoryFactory implements FactoryBean<DocumentBuilderFactory> {
    @Override
    public DocumentBuilderFactory getObject() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setValidating(false);

        return documentBuilderFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return DocumentBuilderFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
