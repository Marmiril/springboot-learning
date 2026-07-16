package com.angel.springbootlearning.exercises.exercise17;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectUnprocessableEntityController {
    @PostMapping("/exercise17/projects")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectResquest request) {

        if (isBlank(request.title())) {
            return badRequest("Project must be titled");
        }
        if (isBlank(request.type())) {
            return badRequest("Project must has a type!");
        }
        if (!isAllowed(request.type())) {
            return unprocessableEntity("Projects` type must be: Maths, History or Music!");
        }
        ProjectResponse response = new ProjectResponse(
                1,
                request.title(),
                request.type());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }
    private boolean isAllowed(String type) { return getAllowedTypes().stream().anyMatch(allowedType -> allowedType.equalsIgnoreCase(type)); }

    private List<String> getAllowedTypes() { return List.of("Math", "History", "Music"); }

    private ResponseEntity<ErrorResponse> badRequest(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<ErrorResponse> unprocessableEntity (String message) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity(errorResponse, status);
    }

    public record CreateProjectResquest(String title, String type) {}
    public record ProjectResponse(int id, String title, String type) {}
    public record ErrorResponse(int status, String error, String message) {}
}
