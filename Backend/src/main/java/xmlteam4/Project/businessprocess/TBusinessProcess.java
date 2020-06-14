
package xmlteam4.Project.businessprocess;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TBusinessProcess complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TBusinessProcess">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="review-cycles">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="review-cycle" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="created" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="scientific-paper-id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TBusinessProcess", namespace = "https://github.com/XML-TIM4/ScientificPublications", propOrder = {
    "reviewCycles"
})
@XmlRootElement(name="business-process", namespace = "https://github.com/XML-TIM4/ScientificPublications" )
public class TBusinessProcess {

    @XmlElement(name = "review-cycles", namespace = "https://github.com/XML-TIM4/ScientificPublications", required = true)
    protected TBusinessProcess.ReviewCycles reviewCycles;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "created")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar created;
    @XmlAttribute(name = "scientific-paper-id")
    protected String scientificPaperId;

    /**
     * Gets the value of the reviewCycles property.
     *
     * @return
     *     possible object is
     *     {@link TBusinessProcess.ReviewCycles }
     *
     */
    public TBusinessProcess.ReviewCycles getReviewCycles() {
        return reviewCycles;
    }

    /**
     * Sets the value of the reviewCycles property.
     *
     * @param value
     *     allowed object is
     *     {@link TBusinessProcess.ReviewCycles }
     *
     */
    public void setReviewCycles(TBusinessProcess.ReviewCycles value) {
        this.reviewCycles = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the created property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getCreated() {
        return created;
    }

    /**
     * Sets the value of the created property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setCreated(XMLGregorianCalendar value) {
        this.created = value;
    }

    /**
     * Gets the value of the scientificPaperId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getScientificPaperId() {
        return scientificPaperId;
    }

    /**
     * Sets the value of the scientificPaperId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setScientificPaperId(String value) {
        this.scientificPaperId = value;
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
     *         &lt;element name="review-cycle" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded"/>
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
        "reviewCycle"
    })
    public static class ReviewCycles {

        @XmlElement(name = "review-cycle", namespace = "https://github.com/XML-TIM4/ScientificPublications", required = true)
        protected List<Object> reviewCycle;

        /**
         * Gets the value of the reviewCycle property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reviewCycle property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReviewCycle().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Object }
         *
         *
         */
        public List<Object> getReviewCycle() {
            if (reviewCycle == null) {
                reviewCycle = new ArrayList<Object>();
            }
            return this.reviewCycle;
        }

    }

}
