
package xmlteam4.Project.businessprocess;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TActorTask complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TActorTask">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="user-type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="author"/>
 *             &lt;enumeration value="editor"/>
 *             &lt;enumeration value="reviewer"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="user-id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="finished" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="document-id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="document-type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="scientific-paper"/>
 *             &lt;enumeration value="review"/>
 *             &lt;enumeration value="cover-letter"/>
 *             &lt;enumeration value="notification"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TActorTask", namespace = "https://github.com/XML-TIM4/ScientificPublications")
public class TActorTask {

    @XmlAttribute(name = "user-type", required = true)
    protected String userType;
    @XmlAttribute(name = "user-id", required = true)
    protected String userId;
    @XmlAttribute(name = "finished")
    protected Boolean finished;
    @XmlAttribute(name = "document-id")
    protected String documentId;
    @XmlAttribute(name = "document-type", required = true)
    protected String documentType;

    public TActorTask() {
    }

    public TActorTask(UserType userType, String userId, DocumentType documentType, String documentId, Boolean finished) {
        this.userType = userType.toString();
        this.userId = userId;
        this.finished = finished;
        this.documentId = documentId;
        this.documentType = documentType.toString();
    }

    /**
     * Gets the value of the userType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUserType(String value) {
        this.userType = value;
    }

    /**
     * Gets the value of the userId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the finished property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isFinished() {
        if (finished == null) {
            return false;
        } else {
            return finished;
        }
    }

    /**
     * Sets the value of the finished property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setFinished(Boolean value) {
        this.finished = value;
    }

    /**
     * Gets the value of the documentId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * Sets the value of the documentId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDocumentId(String value) {
        this.documentId = value;
    }

    /**
     * Gets the value of the documentType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Sets the value of the documentType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDocumentType(String value) {
        this.documentType = value;
    }

}
