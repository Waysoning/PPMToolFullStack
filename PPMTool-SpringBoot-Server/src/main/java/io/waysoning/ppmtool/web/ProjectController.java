package io.waysoning.ppmtool.web;

import io.waysoning.ppmtool.domain.Project;
import io.waysoning.ppmtool.services.MapValidationErrorService;
import io.waysoning.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        Project project1 = projectService.saveOrUpdateProject(project, principal.getName());
//        return new ResponseEntity<>(project, HttpStatus.CREATED);
        return ResponseEntity.ok(project1);
    }


    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIdentifier, Principal principal) {
        Project project = projectService.findProjectByIdentifier(projectIdentifier, principal.getName());
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseBody // Use this annotation to avoid 404 error
    public Iterable<Project> getAllProjects(Principal principal) {
        return projectService.findAllProjects(principal.getName());
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier, Principal principal) {
        projectService.deleteProjectByIdentifier(projectIdentifier, principal.getName());
        return new ResponseEntity<>("Project with ID: '" + projectIdentifier + "' was deleted.", HttpStatus.OK);
    }
}
