/*
 * Exercise 11 - Reusable validation with private methods
 *
 * Purpose:
 * This exercise improves the previous manual validation by moving
 * the validation logic into private methods. This keeps the controller
 * cleaner and prepares the code for a future layered architecture.
 *
 * URL:
 * http://localhost:8080/exercise11/students
 */

package com.angel.springbootlearning.exercises.exercise11;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentReusableValidationController {
    
    @PostMapping("/exercise11/students")
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest request) {
        
        if (hasInvalidRequiredFields(request)) {
            return buildBadRequestResponse("Name and role are required");
        }

        StudentResponse response = new StudentResponse(
            request.name(),
            request.role(),
            "Student created successfully"
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    // Checks whether the request contains invalid mandatory fields.
    private boolean hasInvalidRequiredFields(StudentRequest request) {
        return request == null
            || isBlank(request.name())
            || isBlank(request.role());
    }

    // Checks whether a text value is null, emp`ty or only spaces.
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Builds a reusable HTTP 400 Bad Request response
    private ResponseEntity<ErrorResponse> buildBadRequestResponse(String message) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    public record StudentRequest(String name, String role) {}
    public record StudentResponse(String name, String role, String message) {}
    public record ErrorResponse(int status, String message) { }
}
