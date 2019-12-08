package xmlteam4.Project.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cover-letters")
public class CoverLetterController {

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getCoverLetter(@PathVariable("id") String id) {
        //TODO
    }

    @PostMapping(consumes = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> createCoverLetter(@RequestParam("scientific-paper") String scientificPaperId,
                                                    @RequestBody String xml) {
        // TODO
    }
}
