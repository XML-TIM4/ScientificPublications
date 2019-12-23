package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getReview(@PathVariable("id") String id) {

        String review = "";
        try {
            review = reviewService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createReview(@RequestParam("scientific-paper") String scientificPaperId,
                                               @RequestBody String xml) {

        String review = "";
        try {
            review = reviewService.create(scientificPaperId,xml);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(review,HttpStatus.OK);

    }
}
