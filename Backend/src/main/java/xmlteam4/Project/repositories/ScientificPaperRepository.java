package xmlteam4.Project.repositories;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.DTOs.SearchResultDTO;
import xmlteam4.Project.exceptions.CRUDServiceException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.utilities.exist.CRUDService;
import xmlteam4.Project.utilities.exist.QueryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class ScientificPaperRepository {
    @Value("${scientific-paper-collection-id}")
    private String scientificPaperCollectionId;

    @Autowired
    private CRUDService crudService;

    @Autowired
    private QueryService queryService;


    public String findOne(String id) throws RepositoryException {
        String xPathExp = String.format("//scientific-paper[@id='%s']", id);

        try {
            ResourceSet resultSet = queryService.executeXPathQuery(scientificPaperCollectionId, xPathExp);

            if (resultSet == null)
                return null;

            XMLResource res = queryService.extractSingleResource(resultSet);
            String retVal = res.getContent().toString();
            ((EXistResource) res).freeResources();
            return retVal;
        } catch (XMLDBException e) {
            throw new RepositoryException("Failed to find scientific paper");
        }
    }

    public String create(String id, String scientificPaper) throws RepositoryException {
        try {
            crudService.storeDocument(scientificPaperCollectionId, id, scientificPaper);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to create scientific paper");
        }
        return id;
    }

    public String update(String id, String scientificPaper) throws RepositoryException {
        String oldScientificPaperData = findOne(id);

        if (oldScientificPaperData == null) {
            throw new RepositoryException("Failed to update nonexistent scientific paper");
        }

        delete(id);
        try {
            crudService.storeDocument(scientificPaperCollectionId, id, scientificPaper);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to update scientific paper");
        }
        return id;
    }

    public void delete(String id) throws RepositoryException {
        try {
            crudService.deleteDocument(scientificPaperCollectionId, id);
        } catch (CRUDServiceException e) {
            throw new RepositoryException("Failed to delete scientific paper");
        }
    }

    public SearchResultDTO basicSearch(String searchText) throws RepositoryException {
        String papers = String.format("//scientific-paper[//*[contains(text(), '%s')] " +
                        "and /metadata/status/text() == 'accepted']/@id",
                searchText);

        try {
            ResourceSet papersSet = queryService.executeXPathQuery(scientificPaperCollectionId, papers);

            SearchResultDTO searchResultDTO = new SearchResultDTO();

            if (papersSet != null) {
                List<XMLResource> resources = queryService.extractAllResources(papersSet);

                for (XMLResource res : resources)
                    searchResultDTO.getOwnPaperIds().add(res.getContent().toString());

                ((EXistResource) papersSet).freeResources();
            }

            return searchResultDTO;
        } catch (XMLDBException e) {
            throw new RepositoryException("Failed to search scientific papers");
        }
    }

    public SearchResultDTO basicSearch(String searchText, String authorID) throws RepositoryException {
        String ownPapers = String.format("//scientific-paper[//*[contains(text(), '%s')] " +
                        "and /authors/author/@id == '%s']/@id",
                searchText, authorID);

        String otherPapers = String.format("//scientific-paper[//*[contains(text(), '%s')] " +
                        "and /authors/author/@id != '%s and /metadata/status/text() == 'accepted']/@id",
                searchText, authorID);
        try {
            ResourceSet ownPapersSet = queryService.executeXPathQuery(scientificPaperCollectionId, ownPapers);
            ResourceSet otherPapersSet = queryService.executeXPathQuery(scientificPaperCollectionId, otherPapers);

            SearchResultDTO searchResultDTO = new SearchResultDTO();

            if (ownPapers != null) {
                List<XMLResource> resources = queryService.extractAllResources(ownPapersSet);

                for (XMLResource res : resources)
                    searchResultDTO.getOwnPaperIds().add(res.getContent().toString());

                ((EXistResource) ownPapersSet).freeResources();
            }

            if (otherPapersSet != null) {
                List<XMLResource> resources = queryService.extractAllResources(otherPapersSet);

                for (XMLResource res : resources)
                    searchResultDTO.getOtherPaperIds().add(res.getContent().toString());

                ((EXistResource) otherPapersSet).freeResources();
            }

            return searchResultDTO;
        } catch (XMLDBException e) {
            throw new RepositoryException("Failed to search scientific papers");
        }
    }

    // TODO: advancedSearch(Map<String, String> metadata)
}
