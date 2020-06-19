package xmlteam4.Project.repositories;

import org.apache.commons.text.StringSubstitutor;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import xmlteam4.Project.DTOs.SearchDTO;
import xmlteam4.Project.DTOs.SearchResultDTO;
import xmlteam4.Project.exceptions.CRUDServiceException;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.utilities.exist.CRUDService;
import xmlteam4.Project.utilities.exist.QueryService;
import xmlteam4.Project.utilities.sparql.SparqlService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Repository
public class ScientificPaperRepository {
    @Value("${scientific-paper-collection-id}")
    private String scientificPaperCollectionId;

    @Autowired
    private CRUDService crudService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private SparqlService sparqlService;


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
        String papers = String.format("data(//scientific-paper[.//*/text()[contains(., '%s')] " +
                "and ./metadata/status/text() = 'accepted']/@id)", searchText);

        try {
            ResourceSet papersSet = queryService.executeXPathQuery(scientificPaperCollectionId, papers);

            SearchResultDTO searchResultDTO = new SearchResultDTO();

            if (papersSet != null) {
                List<XMLResource> resources = queryService.extractAllResources(papersSet);

                String id;
                for (XMLResource res : resources) {
                    id = res.getContent().toString();
                    id = id.replace("3030/ScientificPaperDataset", "8080");
                    searchResultDTO.getOtherPaperIds().add(id);
                    ((EXistResource) res).freeResources();
                }
            }

            return searchResultDTO;
        } catch (XMLDBException e) {
            throw new RepositoryException("Scientific papers search failed");
        }
    }


    public SearchResultDTO basicSearchEditor(String searchText) throws RepositoryException {
        String papers = String.format("data(//scientific-paper[.//*/text()[contains(., '%s')] " +
                "and ./metadata/status/text() = 'uploaded']/@id)", searchText);

        try {
            ResourceSet papersSet = queryService.executeXPathQuery(scientificPaperCollectionId, papers);

            SearchResultDTO searchResultDTO = new SearchResultDTO();

            if (papersSet != null) {
                List<XMLResource> resources = queryService.extractAllResources(papersSet);

                String id;
                for (XMLResource res : resources) {
                    id = res.getContent().toString();
                    id = id.replace("3030/ScientificPaperDataset", "8080");
                    searchResultDTO.getOtherPaperIds().add(id);
                    ((EXistResource) res).freeResources();
                }
            }

            return searchResultDTO;
        } catch (XMLDBException e) {
            throw new RepositoryException("Scientific papers search failed");
        }
    }


    public SearchResultDTO basicSearch(String searchText, String authorID) throws RepositoryException {
        String ownPapers = String.format("data(//scientific-paper[.//*/text()[contains(., '%s')] " +
                        "and ./authors/author/@id = '%s']/@id)",
                searchText, authorID);

        String otherPapers = String.format("data(//scientific-paper[.//*/text()[contains(., '%s')] " +
                        "and ./authors/author/@id != '%s' and ./metadata/status/text() = 'accepted']/@id)",
                searchText, authorID);
        try {
            ResourceSet ownPapersSet = queryService.executeXPathQuery(scientificPaperCollectionId, ownPapers);
            ResourceSet otherPapersSet = queryService.executeXPathQuery(scientificPaperCollectionId, otherPapers);

            SearchResultDTO searchResultDTO = new SearchResultDTO();

            if (ownPapers != null) {
                List<XMLResource> resources = queryService.extractAllResources(ownPapersSet);

                String id;
                for (XMLResource res : resources) {
                    id = res.getContent().toString();
                    id = id.replace("3030/ScientificPaperDataset", "8080");
                    searchResultDTO.getOwnPaperIds().add(id);
                    ((EXistResource) res).freeResources();
                }

            }

            if (otherPapersSet != null) {
                List<XMLResource> resources = queryService.extractAllResources(otherPapersSet);

                String id;
                for (XMLResource res : resources) {
                    id = res.getContent().toString();
                    id = id.replace("3030/ScientificPaperDataset", "8080");
                    searchResultDTO.getOtherPaperIds().add(id);
                    ((EXistResource) res).freeResources();
                }
            }

            return searchResultDTO;
        } catch (XMLDBException e) {
            throw new RepositoryException("Scientific papers search failed");
        }
    }

    public SearchResultDTO advancedSearch(SearchDTO searchDTO) {
        String condition = "graph ?g { ?s <https://schema.org/datePublished> ${accepted} . " +
                "?s <https://schema.org/dateCreated> ${received} . " +
                "?s <https://schema.org/dateModified> ${revised} . " +
                "?s <https://schema.org/version> ${version} . " +
                "?s <https://schema.org/creativeWorkStatus> ?status . " +
                "?s <https://schema.org/genre> ${category} . " +
                "?s <https://schema.org/keywords> ?keywords . " +
                "FILTER(str(?status) = 'accepted') . " +
                "FILTER(CONTAINS(UCASE(str(?keywords)), UCASE(str(${keywords}))))}";

        HashMap<String, String> substitutionMap = new HashMap<>();
        substitutionMap.put("accepted", searchDTO.getAccepted() != null ? searchDTO.getAccepted().toString() :
                "?accepted");
        substitutionMap.put("received", searchDTO.getReceived() != null ? searchDTO.getReceived().toString() :
                "?received");
        substitutionMap.put("revised", searchDTO.getRevised() != null ? searchDTO.getRevised().toString() : "?revised");
        substitutionMap.put("version", searchDTO.getVersion() != null ? searchDTO.getVersion() : "?version");
        substitutionMap.put("category", searchDTO.getCategory() != null ? searchDTO.getCategory().toString() :
                "?category");

        if (searchDTO.getKeywords().isEmpty())
            substitutionMap.put("keywords", "''");
        else
            substitutionMap.put("keywords", String.join(",", searchDTO.getKeywords()));

        StringSubstitutor substitutor = new StringSubstitutor(substitutionMap);
        System.out.println(substitutor.replace(condition));
        ArrayList<String> graphNames = sparqlService.queryAll(substitutor.replace(condition));
        SearchResultDTO searchResult = new SearchResultDTO();
        searchResult.setOtherPaperIds(graphNames);

        for(int i = 0; i < searchResult.getOwnPaperIds().size(); i++){
            searchResult.getOwnPaperIds().set(i,searchResult.getOwnPaperIds().get(i).replace("http://localhost:8080/scientific-papers/",""));
        }

        for(int i = 0; i < searchResult.getOtherPaperIds().size(); i++){
            searchResult.getOtherPaperIds().set(i,searchResult.getOtherPaperIds().get(i).replace("http://localhost:8080/scientific-papers/",""));
        }


        return searchResult;
    }

    public SearchResultDTO advancedSearchEditor(SearchDTO searchDTO) {
        String condition = "graph ?g { ?s <https://schema.org/datePublished> ${accepted} . " +
                "?s <https://schema.org/dateCreated> ${received} . " +
                "?s <https://schema.org/dateModified> ${revised} . " +
                "?s <https://schema.org/version> ${version} . " +
                "?s <https://schema.org/creativeWorkStatus> ?status . " +
                "?s <https://schema.org/genre> ${category} . " +
                "?s <https://schema.org/keywords> ?keywords . " +
                "FILTER(str(?status) = 'uploaded') . " +
                "FILTER(CONTAINS(UCASE(str(?keywords)), UCASE(str(${keywords}))))}";

        HashMap<String, String> substitutionMap = new HashMap<>();
        substitutionMap.put("accepted", searchDTO.getAccepted() != null ? searchDTO.getAccepted().toString() :
                "?accepted");
        substitutionMap.put("received", searchDTO.getReceived() != null ? searchDTO.getReceived().toString() :
                "?received");
        substitutionMap.put("revised", searchDTO.getRevised() != null ? searchDTO.getRevised().toString() : "?revised");
        substitutionMap.put("version", searchDTO.getVersion() != null ? searchDTO.getVersion() : "?version");
        substitutionMap.put("category", searchDTO.getCategory() != null ? searchDTO.getCategory().toString() :
                "?category");

        if (searchDTO.getKeywords().isEmpty())
            substitutionMap.put("keywords", "''");
        else
            substitutionMap.put("keywords", String.join(",", searchDTO.getKeywords()));

        StringSubstitutor substitutor = new StringSubstitutor(substitutionMap);
        System.out.println(substitutor.replace(condition));
        ArrayList<String> graphNames = sparqlService.queryAll(substitutor.replace(condition));
        SearchResultDTO searchResult = new SearchResultDTO();
        searchResult.setOtherPaperIds(graphNames);

        for(int i = 0; i < searchResult.getOwnPaperIds().size(); i++){
            searchResult.getOwnPaperIds().set(i,searchResult.getOwnPaperIds().get(i).replace("http://localhost:8080/scientific-papers/",""));
        }

        for(int i = 0; i < searchResult.getOtherPaperIds().size(); i++){
            searchResult.getOtherPaperIds().set(i,searchResult.getOtherPaperIds().get(i).replace("http://localhost:8080/scientific-papers/",""));
        }


        return searchResult;
    }



    public SearchResultDTO advancedSearch(SearchDTO searchDTO, String authorID) {
        String condition = "graph ?g { ?s <https://schema.org/datePublished> ${accepted} . " +
                "?s <https://schema.org/dateCreated> ${received} . " +
                "?s <https://schema.org/dateModified> ${revised} . " +
                "?s <https://schema.org/version> ${version} . " +
                "?s <https://schema.org/creativeWorkStatus> ?status . " +
                "?s <https://schema.org/genre> ${category} . " +
                "?s <https://schema.org/keywords> ?keywords . " +
                "?s <https://schema.org/author> ?author . " +
                "FILTER(str(?author) = '${author}') . " +
                "FILTER(CONTAINS(UCASE(str(?keywords)), UCASE(str(${keywords}))))}";

        HashMap<String, String> substitutionMap = new HashMap<>();
        substitutionMap.put("accepted", searchDTO.getAccepted() != null ? searchDTO.getAccepted().toString() :
                "?accepted");
        substitutionMap
                .put("received", searchDTO.getReceived() != null ? searchDTO.getReceived().toString() : "?received");
        substitutionMap.put("revised", searchDTO.getRevised() != null ? searchDTO.getRevised().toString() : "?revised");
        substitutionMap.put("version", searchDTO.getVersion() != null ? searchDTO.getVersion() : "?version");
        substitutionMap.put("category", searchDTO.getCategory() != null ? searchDTO.getCategory().toString() :
                "?category");

        if (searchDTO.getKeywords().isEmpty())
            substitutionMap.put("keywords", "''");
        else
            substitutionMap.put("keywords", String.join(",", searchDTO.getKeywords()));

        substitutionMap.put("status", searchDTO.getStatus() != null ? searchDTO.getStatus().name() : "?status");
        substitutionMap.put("author", authorID);


        StringSubstitutor substitutor = new StringSubstitutor(substitutionMap);
        ArrayList<String> graphNames = sparqlService.queryAll(substitutor.replace(condition));
        SearchResultDTO searchResult = new SearchResultDTO();
        searchResult.setOwnPaperIds(graphNames);

        condition = "graph ?g { ?s <https://schema.org/datePublished> ${accepted} . " +
                "?s <https://schema.org/dateCreated> ${received} . " +
                "?s <https://schema.org/dateModified> ${revised} . " +
                "?s <https://schema.org/version> ${version} . " +
                "?s <https://schema.org/creativeWorkStatus> ?status . " +
                "?s <https://schema.org/genre> ${category} . " +
                "?s <https://schema.org/keywords> ?keywords . " +
                "?s <https://schema.org/author> ?author . " +
                "FILTER(str(?status) = 'accepted') . " +
                "FILTER(CONTAINS(UCASE(str(?keywords)), UCASE(str(${keywords})))) . " +
                "FILTER(?author != '${author}')}";

        substitutionMap.remove("status");
        substitutor = new StringSubstitutor(substitutionMap);
        graphNames = sparqlService.queryAll(substitutor.replace(condition));
        searchResult.setOtherPaperIds(graphNames);

        for(int i = 0; i < searchResult.getOwnPaperIds().size(); i++){
            searchResult.getOwnPaperIds().set(i,searchResult.getOwnPaperIds().get(i).replace("http://localhost:8080/scientific-papers/",""));
        }

        for(int i = 0; i < searchResult.getOtherPaperIds().size(); i++){
            searchResult.getOtherPaperIds().set(i,searchResult.getOtherPaperIds().get(i).replace("http://localhost:8080/scientific-papers/",""));
        }


        return searchResult;
    }
}
