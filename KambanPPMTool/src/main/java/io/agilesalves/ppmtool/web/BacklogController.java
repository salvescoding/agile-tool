package io.agilesalves.ppmtool.web;


import io.agilesalves.ppmtool.domain.Backlog;
import io.agilesalves.ppmtool.domain.ProjectTask;
import io.agilesalves.ppmtool.services.MapValidationErrorsService;
import io.agilesalves.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorsService mapValidationErrorsService;

    @PostMapping("/{backlog_identifier}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask,
                                                     BindingResult result, @PathVariable String backlog_identifier) {

        ResponseEntity<?> errorsMap = mapValidationErrorsService.mapValidationError(result);
        if (errorsMap != null) return errorsMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_identifier, projectTask);

        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

}
