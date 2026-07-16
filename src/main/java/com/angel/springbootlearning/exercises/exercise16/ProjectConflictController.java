package com.angel.springbootlearning.exercises.exercise16;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectConflictController {
    @PostMapping("/exercise16/projects")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequest request) {
        if (isBlank(request.title())) { return badRequest("Project must be named!"); }
        if (isBlank(request.type())) { return badRequest("Project must has a type"); }
        if (alreadyExists(request.title())) { return conflict("Title already exists"); }

        ProjectResponse response = new ProjectResponse(
            4,
            request.title(),
            request.type()
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private List<ProjectResponse> getExistingProjects() {
        return List.of(
            new ProjectResponse(1, "Pi...", "Maths"),
            new ProjectResponse(2, "Vivaldi", "Music"),
            new ProjectResponse(3, "Karak-Urum", "Geography")
        );
    }

    private boolean alreadyExists(String title) {
        return getExistingProjects().stream()
            .anyMatch(project -> project.title().equalsIgnoreCase(title));
    }

    private boolean isBlank(String value) { return value == null || value.isBlank();}

    private ResponseEntity<ErrorResponse> badRequest(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<ErrorResponse> conflict (String message) {
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    public record CreateProjectRequest(String title, String type) {}
    public record ProjectResponse(int id, String title, String type) {}
    public record ErrorResponse(int status, String error, String message) {}

}
