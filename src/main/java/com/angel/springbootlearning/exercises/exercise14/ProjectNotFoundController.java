package com.angel.springbootlearning.exercises.exercise14;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectNotFoundController {

    @GetMapping("/exercise14/projects/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable int id) {

        ProjectResponse project = findProjectById(id);

        if (project == null) { return notFound("There is no project with such id: " + id); }
        
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    
    private List<ProjectResponse> getProjects() {
        return List.of(
            new ProjectResponse(1, "Pollocop", "Polliti"),
            new ProjectResponse(2, "Polloman", "Pollocio"),
            new ProjectResponse(3, "PollikiBoy", "Pollas")
        );
    }

    private ProjectResponse findProjectById(int id) {
        return getProjects().stream()
            .filter(project -> project.id() == id)
            .findFirst()
            .orElse(null);
    }

    private ResponseEntity<ErrorResponse> notFound(String message) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    public record ProjectResponse(int id, String title, String type) {}
    public record ErrorResponse(int status, String error, String message) {}
}
