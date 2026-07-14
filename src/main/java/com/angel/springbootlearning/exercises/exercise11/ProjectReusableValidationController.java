package com.angel.springbootlearning.exercises.exercise11;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectReusableValidationController {
    
    @PostMapping("/exercise11/projects")
    public ResponseEntity<?> createPorject(@RequestBody CreateProjectRequest request) {

        if (isBlank(request.title())) { return badRequest("The project must has a title!"); }
        if (isBlank(request.type())) { return badRequest("Project must has a type!"); }

        ProjectResponse response = new ProjectResponse(
            1,
            request.title(),
            request.type()
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<ErrorResponse> badRequest(String message) {
        return new ResponseEntity<> (
            new ErrorResponse(message),
            HttpStatus.BAD_REQUEST
        );
    }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }

    public record CreateProjectRequest(String title, String type) {}
    public record ProjectResponse(int id, String title, String type) {}
    public record ErrorResponse(String message) {}
}
