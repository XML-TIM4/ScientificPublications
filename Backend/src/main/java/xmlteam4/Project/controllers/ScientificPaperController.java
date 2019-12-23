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
        String paper = "";
        try {
            paper = scientificPaperService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(paper, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createScientificPaper(@RequestBody String xml) {
        String paper = "";
        try {
            paper = scientificPaperService.create(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(paper, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> editScientificPaper(@PathVariable("id") String id, @RequestBody String xml) {
        String paper = "";
        try {
            paper = scientificPaperService.update(id,xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(paper, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteScientificPaper(@PathVariable("id") String id) {
        Boolean paper = false;
        try {
            paper = scientificPaperService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(paper, HttpStatus.OK);
    }
}
