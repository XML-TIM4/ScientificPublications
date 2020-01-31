package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xmlteam4.Project.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "/{id}", produces = {MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<Object> getReview(@PathVariable("id") String id,
                                            @RequestHeader HttpHeaders httpHeaders) {

        MediaType contentType = httpHeaders.getContentType();

        try {
            if (MediaType.TEXT_HTML.equals(contentType)) {
                return new ResponseEntity<>(reviewService.getReviewHTML(id), HttpStatus.OK);
            } else if (MediaType.APPLICATION_XML.equals(contentType)) {
                return new ResponseEntity<>(reviewService.getReviewXML(id), HttpStatus.OK);
            } else if (MediaType.APPLICATION_PDF.equals(contentType)) {
                return new ResponseEntity<>(reviewService.getReviewPDF(id), HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid content media type", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Secured("ROLE_EDITOR")
    @PostMapping(consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createReviewTemplate(@RequestBody String xml) {
        try {
            return new ResponseEntity<>(reviewService.createReviewTemplate(xml), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Secured("ROLE_AUTHOR")
    @PutMapping(value = "/{template-id}", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createReview(@PathVariable("template-id") String templateId,
                                               @RequestBody String xml) {
        try {
            // TODO: make creatReview(String templateId, String xml);
            return new ResponseEntity<>(reviewService.createReviewTemplate(xml), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
