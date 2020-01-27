package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createScientificPaper(@RequestBody String xml) {
        try {
            return new ResponseEntity<>(scientificPaperService.createScientificPaper(xml), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> reviseScientificPaper(@PathVariable("id") String id, @RequestBody String xml) {
        try {
            return new ResponseEntity<>(scientificPaperService.reviseScientificPaper(id, xml), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> withdrawScientificPaper(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(scientificPaperService.withdrawScientificPaper(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
