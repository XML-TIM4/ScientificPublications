
package xmlteam4.Project.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xmlteam4.Project.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Users_QNAME = new QName("https://github.com/XML-TIM4/ScientificPublications", "users");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xmlteam4.Project.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TUsers }
     * 
     */
    public TUsers createTUsers() {
        return new TUsers();
    }

    /**
     * Create an instance of {@link TPublicationRoles }
     * 
     */
    public TPublicationRoles createTPublicationRoles() {
        return new TPublicationRoles();
    }

    /**
     * Create an instance of {@link TPublicationRole }
     * 
     */
    public TPublicationRole createTPublicationRole() {
        return new TPublicationRole();
    }

    /**
     * Create an instance of {@link TUser }
     * 
     */
    public TUser createTUser() {
        return new TUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/XML-TIM4/ScientificPublications", name = "users")
    public JAXBElement<TUsers> createUsers(TUsers value) {
        return new JAXBElement<TUsers>(_Users_QNAME, TUsers.class, null, value);
    }

}
