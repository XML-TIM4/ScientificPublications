
package xmlteam4.Project.businessprocess;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPhase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPhase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actor-tasks">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="actor-task" type="{https://github.com/XML-TIM4/ScientificPublications}TActorTask" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="title" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="submitted"/>
 *             &lt;enumeration value="review"/>
 *             &lt;enumeration value="reviewed"/>
 *             &lt;enumeration value="rejected"/>
 *             &lt;enumeration value="accepted"/>
 *             &lt;enumeration value="revise"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="can-advance" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPhase", namespace = "https://github.com/XML-TIM4/ScientificPublications", propOrder = {
    "actorTasks"
})
public class TPhase {

    @XmlElement(name = "actor-tasks", namespace = "https://github.com/XML-TIM4/ScientificPublications", required = true)
    protected TPhase.ActorTasks actorTasks;
    @XmlAttribute(name = "title", required = true)
    protected String title;
    @XmlAttribute(name = "can-advance")
    protected Boolean canAdvance;

    /**
     * Gets the value of the actorTasks property.
     * 
     * @return
     *     possible object is
     *     {@link TPhase.ActorTasks }
     *     
     */
    public TPhase.ActorTasks getActorTasks() {
        return actorTasks;
    }

    /**
     * Sets the value of the actorTasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPhase.ActorTasks }
     *     
     */
    public void setActorTasks(TPhase.ActorTasks value) {
        this.actorTasks = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the canAdvance property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCanAdvance() {
        if (canAdvance == null) {
            return false;
        } else {
            return canAdvance;
        }
    }

    /**
     * Sets the value of the canAdvance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCanAdvance(Boolean value) {
        this.canAdvance = value;
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
     *         &lt;element name="actor-task" type="{https://github.com/XML-TIM4/ScientificPublications}TActorTask" maxOccurs="unbounded" minOccurs="0"/>
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
        "actorTask"
    })
    public static class ActorTasks {

        @XmlElement(name = "actor-task", namespace = "https://github.com/XML-TIM4/ScientificPublications")
        protected List<TActorTask> actorTask;

        /**
         * Gets the value of the actorTask property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the actorTask property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getActorTask().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TActorTask }
         * 
         * 
         */
        public List<TActorTask> getActorTask() {
            if (actorTask == null) {
                actorTask = new ArrayList<TActorTask>();
            }
            return this.actorTask;
        }

    }

}
