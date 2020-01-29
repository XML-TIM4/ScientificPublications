package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import xmlteam4.Project.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @GetMapping(value = "/{id}", produces = {MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<String> getReview(@PathVariable("id") String id,
                                            @RequestHeader HttpHeaders httpHeaders) {

        MediaType contentType = httpHeaders.getContentType();


        try {
            if (MediaType.TEXT_HTML.equals(contentType)) {
                return new ResponseEntity<>(reviewService.getReviewHTML(id), HttpStatus.OK);
            } else if (MediaType.APPLICATION_XML.equals(contentType)) {
                return new ResponseEntity<>(reviewService.getReviewXML(id), HttpStatus.OK);
            } else if (MediaType.APPLICATION_PDF.equals(contentType)) {
                return new ResponseEntity(reviewService.getReviewPDF(id), HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid content media type", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createReview(@RequestParam("review") String reviewId,
                                               @RequestBody String xml) {


        try {
            return new ResponseEntity<>(reviewService.create(reviewId,xml),HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
