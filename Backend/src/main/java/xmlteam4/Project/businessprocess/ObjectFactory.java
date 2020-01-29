
package xmlteam4.Project.businessprocess;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xmlteam4.Project.businessprocess package. 
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

    private final static QName _BusinessProcess_QNAME = new QName("https://github.com/XML-TIM4/ScientificPublications", "business-process");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xmlteam4.Project.businessprocess
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TPhase }
     * 
     */
    public TPhase createTPhase() {
        return new TPhase();
    }

    /**
     * Create an instance of {@link TReviewCycle }
     * 
     */
    public TReviewCycle createTReviewCycle() {
        return new TReviewCycle();
    }

    /**
     * Create an instance of {@link TBusinessProcess }
     * 
     */
    public TBusinessProcess createTBusinessProcess() {
        return new TBusinessProcess();
    }

    /**
     * Create an instance of {@link TActorTask }
     * 
     */
    public TActorTask createTActorTask() {
        return new TActorTask();
    }

    /**
     * Create an instance of {@link TPhase.ActorTasks }
     * 
     */
    public TPhase.ActorTasks createTPhaseActorTasks() {
        return new TPhase.ActorTasks();
    }

    /**
     * Create an instance of {@link TReviewCycle.Phases }
     * 
     */
    public TReviewCycle.Phases createTReviewCyclePhases() {
        return new TReviewCycle.Phases();
    }

    /**
     * Create an instance of {@link TBusinessProcess.ReviewCycles }
     * 
     */
    public TBusinessProcess.ReviewCycles createTBusinessProcessReviewCycles() {
        return new TBusinessProcess.ReviewCycles();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TBusinessProcess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/XML-TIM4/ScientificPublications", name = "business-process")
    public JAXBElement<TBusinessProcess> createBusinessProcess(TBusinessProcess value) {
        return new JAXBElement<TBusinessProcess>(_BusinessProcess_QNAME, TBusinessProcess.class, null, value);
    }

}
