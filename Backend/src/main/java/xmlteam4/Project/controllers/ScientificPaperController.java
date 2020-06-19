package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xmlteam4.Project.DTOs.SearchDTO;
import xmlteam4.Project.DTOs.SearchResultDTO;
import xmlteam4.Project.exceptions.RepositoryException;
import xmlteam4.Project.model.ScientificPaperStatus;
import xmlteam4.Project.services.ScientificPaperService;


@RestController
@RequestMapping("/scientific-papers")
public class ScientificPaperController {
    @Autowired
    private ScientificPaperService scientificPaperService;

    @GetMapping(value = "/{id}", produces = {MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<Object> getScientificPaper(@PathVariable("id") String id,
                                                     @RequestHeader HttpHeaders httpHeaders) {
        MediaType contentType = httpHeaders.getContentType();

        try {
            if (MediaType.TEXT_HTML.equals(contentType)) {
                return new ResponseEntity<>(scientificPaperService.getScientificPaperHTML(id), HttpStatus.OK);
            } else if (MediaType.APPLICATION_XML.equals(contentType)) {
                return new ResponseEntity<>(scientificPaperService.getScientificPaperXML(id), HttpStatus.OK);
            } else if (MediaType.APPLICATION_PDF.equals(contentType)) {
                return new ResponseEntity<>(scientificPaperService.getScientificPaperPDF(id), HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid content media type", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Secured("ROLE_AUTHOR")
    @PostMapping(consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createScientificPaper(@RequestBody String xml) {
        System.out.println("JOJO\n" + xml);
        try {
            return new ResponseEntity<>(scientificPaperService.createScientificPaper(xml), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Secured("ROLE_AUTHOR")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> reviseScientificPaper(@PathVariable("id") String id, @RequestBody String xml) {
        System.out.println("JOJO\n" + xml);
        try {
            return new ResponseEntity<>(scientificPaperService.reviseScientificPaper(id, xml), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Secured("ROLE_AUTHOR")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> withdrawScientificPaper(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(scientificPaperService.withdrawScientificPaper(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/search")
    public ResponseEntity<Object> searchScientificPapers(@RequestBody SearchDTO searchDTO) {
        try {
            SearchResultDTO searchResultDTO;
            if (searchDTO.getBasic())
                searchResultDTO = scientificPaperService.basicScientificPaperSearch(searchDTO);
            else
                searchResultDTO = scientificPaperService.advancedScientificPaperSearch(searchDTO);

            return new ResponseEntity<>(searchResultDTO, HttpStatus.OK);
        } catch (RepositoryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/search-editor")
    public ResponseEntity<Object> searchScientificPapersEditor(@RequestBody SearchDTO searchDTO) {
        try {
            SearchResultDTO searchResultDTO;
            if (searchDTO.getBasic())
                searchResultDTO = scientificPaperService.basicScientificPaperSearchEditor(searchDTO);
            else
                searchResultDTO = scientificPaperService.advancedScientificPaperSearchEditor(searchDTO);

            return new ResponseEntity<>(searchResultDTO, HttpStatus.OK);
        } catch (RepositoryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Secured("ROLE_EDITOR")
    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> decideForPaper(@PathVariable String id,
                                                 @RequestParam ScientificPaperStatus decision) {
        try {
            return new ResponseEntity<>(scientificPaperService.decideForPaper(id, decision), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
