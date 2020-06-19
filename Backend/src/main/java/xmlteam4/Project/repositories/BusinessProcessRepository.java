package xmlteam4.Project.repositories;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.businessprocess.*;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.utilities.exist.CRUDService;
import xmlteam4.Project.utilities.exist.QueryService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BusinessProcessRepository {
    @Value("${business-process-collection-id}")
    private String businessProcessCollectionId;

    @Autowired
    private CRUDService crudService;

    @Autowired
    private QueryService queryService;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public TBusinessProcess findOneById(String id) throws RepositoryException {
        String xPathExp = String.format("//business-process[@id='%s']", "business-processes/" + id);
        try {
            ResourceSet resultSet = queryService.executeXPathQuery(businessProcessCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            ResourceIterator i = resultSet.getIterator();
            XMLResource res = null;
            TBusinessProcess retVal = null;

            if (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
                retVal = unmarshallBusinessProcess(res.getContentAsDOM());
            }

            if (res != null)
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException exception) {
                    exception.printStackTrace();
                }

            return retVal;
        } catch (XMLDBException | JAXBException e) {
            throw new RepositoryException("Failed to find business process");
        }
    }

    public void createBusinessProcess(String scientificPaperId, String authorId) throws RepositoryException {
        try {
            ObjectFactory objectFactory = new ObjectFactory();

            TBusinessProcess newBussinessProcess = objectFactory.createTBusinessProcess();
            newBussinessProcess.setCreated(DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(dateTimeFormatter.format(LocalDateTime.now())));
            newBussinessProcess.setScientificPaperId(scientificPaperId);
            newBussinessProcess.setId("business-processes/" + scientificPaperId);

            TBusinessProcess.ReviewCycles tReviewCycles = objectFactory.createTBusinessProcessReviewCycles();
            newBussinessProcess.setReviewCycles(tReviewCycles);

            TReviewCycle cycle = createNewReviewCycle(authorId, scientificPaperId);
            tReviewCycles.getReviewCycle().add(cycle);

            save(newBussinessProcess);
        } catch (JAXBException | DatatypeConfigurationException | XMLDBException e) {
            throw new RepositoryException("Failed to create new business process");
        }
    }

    public void updateBusinessProcess(TBusinessProcess businessProcess) throws RepositoryException {
        try {
            Collection col = crudService.getOrCreateCollection(businessProcessCollectionId, 0);
            XMLResource resource =
                    (XMLResource) col.getResource(businessProcess.getId().replace("business-processes/", "") + ".xml");
            resource.setContent(marshallBusinessProcess(businessProcess));
            col.storeResource(resource);
        } catch (XMLDBException | JAXBException e) {
            throw new RepositoryException("Failed to update business process");
        }
    }

    public TReviewCycle createNewReviewCycle(String authorId, String scientificPaperId) {
        ObjectFactory objectFactory = new ObjectFactory();
        TReviewCycle cycle = objectFactory.createTReviewCycle();
        cycle.setStatus(ReviewCycleStatus.PENDING.toString());

        TReviewCycle.Phases phases = objectFactory.createTReviewCyclePhases();

        cycle.setPhases(phases);

        // create submitted phase
        // author should create scientific paper and cover letter
        // editor should create review template
        TPhase firstPhase = objectFactory.createTPhase();
        firstPhase.setCanAdvance(false);
        firstPhase.setTitle(PhaseTitle.SUBMITTED.toString());
        phases.getPhase().add(firstPhase);
        // create actor tasks for phase submitted
        TPhase.ActorTasks actorTasks = objectFactory.createTPhaseActorTasks();

        // author created scientific paper
        TActorTask createScientificPaper = new TActorTask(UserType.AUTHOR, authorId, DocumentType.SCIENTIFIC_PAPER,
                scientificPaperId, true);

        // author should create cover letter
        TActorTask createCoverLetter = new TActorTask(UserType.AUTHOR, authorId, DocumentType.COVER_LETTER,
                "", false);

        // editor should create review template
        TActorTask createReviewTemplate = new TActorTask(UserType.EDITOR, "", DocumentType.REVIEW,
                "", false);

        actorTasks.getActorTask().add(createScientificPaper);
        actorTasks.getActorTask().add(createCoverLetter);
        actorTasks.getActorTask().add(createReviewTemplate);

        firstPhase.setActorTasks(actorTasks);

        return cycle;
    }

    private void save(TBusinessProcess businessProcess) throws XMLDBException, JAXBException {
        Collection col = crudService.getOrCreateCollection(businessProcessCollectionId, 0);
        XMLResource resource = (XMLResource) col
                .createResource(businessProcess.getId() + ".xml", XMLResource.RESOURCE_TYPE);
        resource.setContent(marshallBusinessProcess(businessProcess));
        col.storeResource(resource);
    }

    private String marshallBusinessProcess(TBusinessProcess businessProcess) throws JAXBException {
        OutputStream os = new ByteArrayOutputStream();
        JAXBContext context = JAXBContext.newInstance("xmlteam4.Project.businessprocess");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(businessProcess, os);

        return os.toString();
    }

    private TBusinessProcess unmarshallBusinessProcess(Node node) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TBusinessProcess.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (TBusinessProcess) unmarshaller.unmarshal(node);
    }

    public List<String> getOwnReviewsIds(String userId) {
        String xPathExp = String
                .format("data(//business-process[.//review-cycle[last() and ./@status = 'pending' and .//phase[last()" +
                        " and ./@title = 'review' and ./@can-advance = 'false' and .//actor-task/@user-id = " +
                        "'%s']]]/@scientific-paper-id)", userId);
        try {
            ResourceSet resultSet = queryService.executeXPathQuery(businessProcessCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            ResourceIterator i = resultSet.getIterator();
            XMLResource res = null;
            List<String> retVal = new ArrayList<>();

            while (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
                retVal.add(res.getContent().toString());
            }

            if (res != null)
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException exception) {
                    exception.printStackTrace();
                }

            return retVal;
        } catch (XMLDBException e) {
            return new ArrayList<>();
        }
    }

    public List<String> getFinishedReviewsIds() {
        String xPathExp = "data(//business-process[.//review-cycle[last() and ./@status = 'reviewed']]/@scientific-paper-id)";
        try {
            ResourceSet resultSet = queryService.executeXPathQuery(businessProcessCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            ResourceIterator i = resultSet.getIterator();
            XMLResource res = null;
            List<String> retVal = new ArrayList<>();

            while (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
                retVal.add(res.getContent().toString());
            }

            if (res != null)
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException exception) {
                    exception.printStackTrace();
                }

            return retVal;
        } catch (XMLDBException e) {
            return new ArrayList<>();
        }
    }

    public String getPaperCreatorId(String scientificPaperId) throws RepositoryException {
        String xPathExp = String
                .format("data(//business-process[@scientific-paper-id='%s']//review-cycle[last()]//phase[@title = " +
                        "'submitted']//actor-task[@document-type = 'scientific-paper']/@user-id)", scientificPaperId);
        try {
            ResourceSet resultSet = queryService.executeXPathQuery(businessProcessCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            ResourceIterator i = resultSet.getIterator();
            XMLResource res = null;
            String retVal = null;

            if (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
                retVal = res.getContent().toString();
            }

            if (res != null)
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException exception) {
                    exception.printStackTrace();
                }

            return retVal;
        } catch (XMLDBException e) {
            throw new RepositoryException("Failed to find scientific paper creator");
        }
    }
}
