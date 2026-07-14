package com.angel.springbootlearning.exercises.exercise12;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectSeveralFieldsValidationController {

    @PostMapping("/exercise12/projects")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequest request) {

        if (isBlank(request.title())) { return badRequest("Project must be named!"); }
        if (isBlank(request.type())) { return badRequest("Project must be a type of..."); }
        if (isInvalidDifficulty(request.difficulty())) { return badRequest("Difficulty must be between 5-10."); }
        if (isInvalidEmail(request.email())) { return badRequest("The prject has no valid email!"); }

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
    private boolean isInvalidEmail(String email) { return isBlank(email) || !email.contains("@") || !email.contains(".");}

    private ResponseEntity<ErrorResponse> badRequest(String message) {
        return new ResponseEntity<>(
            new ErrorResponse(message),
            HttpStatus.BAD_REQUEST
        );
    }

    public record CreateProjectRequest(String title, String type, Integer difficulty, String email) {}
    public record ProjectResponse(int id, String title, String type, Integer difficulty, String email) {}
    public record ErrorResponse(String message) {}
}
