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
        String cover = "";
        try {
            cover = coverLetterService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(cover, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createCoverLetter(@RequestParam("scientific-paper") String scientificPaperId,
                                                    @RequestBody String xml) {
        String cover = "";
        try {
            cover = coverLetterService.create(scientificPaperId,xml);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(cover,HttpStatus.OK);
    }
}
