
package xmlteam4.Project.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPublicationRoles complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPublicationRoles">
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
@XmlType(name = "TPublicationRoles", namespace = "https://github.com/XML-TIM4/ScientificPublications", propOrder = {
    "publicationRole"
})
public class TPublicationRoles {

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
