
package xmlteam4.Project.businessprocess;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TReviewCycle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TReviewCycle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="phases">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="phase" type="{https://github.com/XML-TIM4/ScientificPublications}TPhase" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="status" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="pending"/>
 *             &lt;enumeration value="rejected"/>
 *             &lt;enumeration value="accepted"/>
 *             &lt;enumeration value="revise"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TReviewCycle", namespace = "https://github.com/XML-TIM4/ScientificPublications", propOrder = {
    "phases"
})
public class TReviewCycle {

    @XmlElement(namespace = "https://github.com/XML-TIM4/ScientificPublications", required = true)
    protected TReviewCycle.Phases phases;
    @XmlAttribute(name = "status", required = true)
    protected String status;

    /**
     * Gets the value of the phases property.
     * 
     * @return
     *     possible object is
     *     {@link TReviewCycle.Phases }
     *     
     */
    public TReviewCycle.Phases getPhases() {
        return phases;
    }

    /**
     * Sets the value of the phases property.
     * 
     * @param value
     *     allowed object is
     *     {@link TReviewCycle.Phases }
     *     
     */
    public void setPhases(TReviewCycle.Phases value) {
        this.phases = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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
     *         &lt;element name="phase" type="{https://github.com/XML-TIM4/ScientificPublications}TPhase" maxOccurs="unbounded"/>
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
        "phase"
    })
    public static class Phases {

        @XmlElement(namespace = "https://github.com/XML-TIM4/ScientificPublications", required = true)
        protected List<TPhase> phase;

        /**
         * Gets the value of the phase property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the phase property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPhase().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TPhase }
         * 
         * 
         */
        public List<TPhase> getPhase() {
            if (phase == null) {
                phase = new ArrayList<TPhase>();
            }
            return this.phase;
        }

    }

}
