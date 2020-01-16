package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import xmlteam4.Project.services.ScientificPaperService;

@RestController
@RequestMapping("/scientific-papers")
public class ScientificPaperController {

    @Autowired
    private ScientificPaperService scientificPaperService;

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getScientificPaper(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(scientificPaperService.findOne(id),HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>("Failed to find scientific paper with given id", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createScientificPaper(@RequestBody String xml) {
        try {
            return new ResponseEntity<>(scientificPaperService.create(xml),HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>("Failed to create scientific paper", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> editScientificPaper(@PathVariable("id") String id, @RequestBody String xml) {
        try {
            return new ResponseEntity<>(scientificPaperService.update(id,xml),HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>("Failed to update scientific paper with given id", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteScientificPaper(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(scientificPaperService.delete(id),HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
