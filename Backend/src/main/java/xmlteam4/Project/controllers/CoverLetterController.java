package xmlteam4.Project.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RestController
@RequestMapping("/cover-letters")
public class CoverLetterController {

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getCoverLetter(@PathVariable("id") String id) {
        throw new NotImplementedException();
    }

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createCoverLetter(@RequestParam("scientific-paper") String scientificPaperId,
                                                    @RequestBody String xml) {
        throw new NotImplementedException();
    }
}
