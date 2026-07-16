package com.angel.springbootlearning.exercises.exercise19;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectConditionalValidationController {
    @PostMapping("/exercise19/projects")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequest request)  {

        if (isBlank(request.title())) { return badRequest("Project myst be titled!"); }
        if (isBlank(request.type())) { return badRequest("Project mus has a type!"); }
        if (!isAllowed(request.type())) { return unprocessableContent("Project types must be: Maths, Geography & Music"); }
        if (requiresPortfolio(request.type())) { return unprocessableContent("Portfolio is required for Geography & Music"); }

        ProjectResponse response = new ProjectResponse(
            1,
            request.title(),
            request.type(),
            request.portfolioUrl()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private List<String> getAllowedTypes() { return List.of("Maths", "Geography", "Music"); }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }
    private boolean isAllowed(String type) { return getAllowedTypes().stream().anyMatch(allowedtype -> allowedtype.equalsIgnoreCase(type)); }
    private boolean requiresPortfolio(String type) { return type.equalsIgnoreCase("Geography") || type.equalsIgnoreCase("Music"); }

    private ResponseEntity<ErrorResponse> badRequest(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }
    
    private ResponseEntity<ErrorResponse> unprocessableContent(String message) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    public record CreateProjectRequest(String title, String type, String portfolioUrl) {}
    public record ProjectResponse(int id, String title, String type, String portfolioUrl) {}
    public record ErrorResponse(int status, String error, String message) {}
    
}

