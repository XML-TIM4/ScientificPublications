package xmlteam4.Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmlteam4.Project.model.ListObject;
import xmlteam4.Project.services.BusinessProcessService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/business-process")
public class BusinessProcessControler {
    @Autowired
    private BusinessProcessService businessProcessService;

    @Secured("ROLE_AUTHOR")
    @GetMapping(value = "/own")
    public ResponseEntity<Object> getOwnReviewsIds() {
        ListObject list = new ListObject();
        list.setResultIds((ArrayList<String>) businessProcessService.getOwnReviewsIds());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Secured("ROLE_EDITOR")
    @GetMapping(value = "/finished")
    public ResponseEntity<Object> getFinishedReviewsIds() {
        ListObject list = new ListObject();
        list.setResultIds((ArrayList<String>) businessProcessService.getFinishedReviewsIds());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
