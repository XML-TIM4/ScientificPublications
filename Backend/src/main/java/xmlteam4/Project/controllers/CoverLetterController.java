package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import xmlteam4.Project.services.CoverLetterService;

@RestController
@RequestMapping("/cover-letters")
public class CoverLetterController {

    @Autowired
    private CoverLetterService coverLetterService;

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getCoverLetter(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(coverLetterService.findOne(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to find cover letter with given id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createCoverLetter(@RequestParam("scientific-paper") String scientificPaperId,
                                                    @RequestBody String xml) {

        try {
            return new ResponseEntity<>(coverLetterService.create(scientificPaperId,xml),HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>("Failed to create cover letter for given scientific paper", HttpStatus.BAD_REQUEST);
        }

    }
}
