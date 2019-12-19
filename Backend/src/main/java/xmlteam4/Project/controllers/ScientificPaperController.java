package xmlteam4.Project.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RestController
@RequestMapping("/scientific-papers")
public class ScientificPaperController {

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getScientificPaper(@PathVariable("id") String id) {
        throw new NotImplementedException();
    }

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createScientificPaper(@RequestBody String xml) {
        throw new NotImplementedException();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> editScientificPaper(@PathVariable("id") String id, @RequestBody String xml) {
        throw new NotImplementedException();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteScientificPaper(@PathVariable("id") String id) {
        throw new NotImplementedException();
    }
}
