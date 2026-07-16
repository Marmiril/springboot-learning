package com.angel.springbootlearning.exercises.exercise15;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectNoContentController {
    
    @DeleteMapping("/exercise15/projects/{id}")
    public ResponseEntity<?> deleteProjectId(@PathVariable int id) {
        if(!projectExists(id)) { return notFound("Project with id: " + id + " was not found!"); }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private List<ProjectResponse> getProjects() {
        return List.of(
            new ProjectResponse(1, "Pi moment", "Math"),
            new ProjectResponse(2, "Lost Civilizations", "History"),
            new ProjectResponse(3, "Mountains", "Music")
        );
    }

    private boolean projectExists(int id) {
        return getProjects().stream()
            .anyMatch(project -> project.id() == id);
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

