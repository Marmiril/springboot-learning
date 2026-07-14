package com.angel.springbootlearning.exercises.exercise13;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectStructuredErrorController {
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    @PostMapping("/exercise13/projects")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequest request) {
        if (isBlank(request.title())) { return badRequest("Project must be titled!"); }
        if (isBlank(request.email())) { return badRequest("Project must has a type!"); }
        if (isInvalidDifficulty(request.difficulty())) { return badRequest("Difficulty between 5-10..."); }
        if (isInvalidEmail(request.email())) { return badRequest("The project email is not valid..."); }

        ProjectResponse response = new ProjectResponse(
            1,
            request.title(),
            request.type(),
            request.difficulty(),
            request.email()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }
    private boolean isInvalidDifficulty(Integer difficulty) { return difficulty == null || difficulty < 5 || difficulty > 10; }
    private boolean isInvalidEmail(String email) { return isBlank(email) || !email.matches(EMAIL_REGEX); }

    private ResponseEntity<ErrorResponse> badRequest (String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
    return new ResponseEntity<>(errorResponse, status);
    }

    public record CreateProjectRequest(String title, String type, Integer difficulty, String email) {}
    public record ProjectResponse(int id, String title, String type, Integer difficulty, String email) {}
    public record ErrorResponse(int status, String error, String message) {}

}
