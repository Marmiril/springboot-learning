package com.angel.springbootlearning.exercises.exercise20;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectResponseRefactorController {
    
    @PostMapping("/exercise20/projects")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequest request) {

        if (isBlank(request.title())) { return badRequest("Project must be titled!"); }
        if (isBlank(request.type())) { return badRequest("Project must has a type!"); }
        if (!isAllowedType(request.type())) { return unprocessableContent("Types must be: Maths, Geography, Music"); }
        ProjectResponse response = new ProjectResponse(
            1,
            request.title(),
            request.type()
        );
        return created(response);
    }

    private List<String> allowedTypes() { return List.of("Maths", "Geography", "Music"); }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }
    private boolean isAllowedType(String type) { return allowedTypes().stream().anyMatch(allowedType -> allowedType.equalsIgnoreCase((type))); }

    private ResponseEntity<ProjectResponse> created(ProjectResponse response) { return new ResponseEntity<>(response, HttpStatus.CREATED); }
    
    private ResponseEntity<ErrorResponse> badRequest(String message) { return error(HttpStatus.BAD_REQUEST, message);}
    private ResponseEntity<ErrorResponse> unprocessableContent(String message) { return error(HttpStatus.UNPROCESSABLE_CONTENT, message); }
    
    private ResponseEntity<ErrorResponse> error (HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    public record ProjectResponse(int id, String title, String type) {}
    public record CreateProjectRequest(String title, String type) {}
    public record ErrorResponse(int status, String error, String message) {}
}
