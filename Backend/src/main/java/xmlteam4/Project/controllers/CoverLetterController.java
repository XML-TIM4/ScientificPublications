package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xmlteam4.Project.services.CoverLetterService;

@RestController
@RequestMapping("/cover-letters")
public class CoverLetterController {
    @Autowired
    private CoverLetterService coverLetterService;

    @GetMapping(value = "/{id}", produces = {MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<Object> getCoverLetter(@PathVariable("id") String id,
                                                 @RequestHeader HttpHeaders httpHeaders) {
        MediaType contentType = httpHeaders.getContentType();

        try {
            if (MediaType.TEXT_HTML.equals(contentType)) {
                return new ResponseEntity<>(coverLetterService.getCoverLetterHTML(id), HttpStatus.OK);
            } else if (MediaType.APPLICATION_XML.equals(contentType)) {
                return new ResponseEntity<>(coverLetterService.getCoverLetterXML(id), HttpStatus.OK);
            } else if (MediaType.APPLICATION_PDF.equals(contentType)) {
                return new ResponseEntity<>(coverLetterService.getCoverLetterPDF(id), HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid content media type", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/paper/{id}", produces = "text/plain")
    public ResponseEntity<String> getCoverLetterId(@PathVariable("id") String scientificPaperId) {
        try {
            return new ResponseEntity<>(coverLetterService.getCoverLetterId(scientificPaperId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    @Secured("ROLE_AUTHOR")
    @PostMapping()
    public ResponseEntity<String> createCoverLetter(@RequestBody String xml) {
        try {
            return new ResponseEntity<>(coverLetterService.create(xml), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
