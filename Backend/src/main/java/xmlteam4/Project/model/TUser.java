
package xmlteam4.Project.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="^([a-zA-Z0-9@*#]{8,15})$"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="email">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[^@]+@[^\.]+\..+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="publication-roles">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="publication-role" type="{https://github.com/XML-TIM4/ScientificPublications}TPublicationRole" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="author-id">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="(([a-zA-Z][0-9a-zA-Z+\\-\\.]*:)?/{0,2}[0-9a-zA-Z;/?:@&amp;amp;=+$\\.\\-_!~*'()%]+)?( #[0-9a-zA-Z;/?:@&amp;amp;=+$\\.\\-_!~*'()%]+)?"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="is-editor" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TUser", namespace = "https://github.com/XML-TIM4/ScientificPublications", propOrder = {
    "username",
    "password",
    "email",
    "publicationRoles"
})
public class TUser {

    @XmlElement(namespace = "https://github.com/XML-TIM4/ScientificPublications", required = true)
    protected String username;
    @XmlElement(namespace = "https://github.com/XML-TIM4/ScientificPublications", required = true)
    protected String password;
    @XmlElement(namespace = "https://github.com/XML-TIM4/ScientificPublications", required = true)
    protected String email;
    @XmlElement(name = "publication-roles", namespace = "https://github.com/XML-TIM4/ScientificPublications", required = true)
    protected TUser.PublicationRoles publicationRoles;
    @XmlAttribute(name = "author-id")
    protected String authorId;
    @XmlAttribute(name = "is-editor")
    protected Boolean isEditor;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the publicationRoles property.
     * 
     * @return
     *     possible object is
     *     {@link TUser.PublicationRoles }
     *     
     */
    public TUser.PublicationRoles getPublicationRoles() {
        return publicationRoles;
    }

    /**
     * Sets the value of the publicationRoles property.
     * 
     * @param value
     *     allowed object is
     *     {@link TUser.PublicationRoles }
     *     
     */
    public void setPublicationRoles(TUser.PublicationRoles value) {
        this.publicationRoles = value;
    }

    /**
     * Gets the value of the authorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     * Sets the value of the authorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorId(String value) {
        this.authorId = value;
    }

    /**
     * Gets the value of the isEditor property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsEditor() {
        if (isEditor == null) {
            return false;
        } else {
            return isEditor;
        }
    }

    /**
     * Sets the value of the isEditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsEditor(Boolean value) {
        this.isEditor = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="publication-role" type="{https://github.com/XML-TIM4/ScientificPublications}TPublicationRole" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "publicationRole"
    })
    public static class PublicationRoles {

        @XmlElement(name = "publication-role", namespace = "https://github.com/XML-TIM4/ScientificPublications")
        protected List<TPublicationRole> publicationRole;

        /**
         * Gets the value of the publicationRole property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the publicationRole property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPublicationRole().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TPublicationRole }
         * 
         * 
         */
        public List<TPublicationRole> getPublicationRole() {
            if (publicationRole == null) {
                publicationRole = new ArrayList<TPublicationRole>();
            }
            return this.publicationRole;
        }

    }

}
