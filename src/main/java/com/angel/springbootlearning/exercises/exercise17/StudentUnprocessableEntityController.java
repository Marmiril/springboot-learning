/*
 * Exercise 17 - HTTP 422 Unprocessable Entity
 *
 * Purpose:
 * This exercise introduces how to return an HTTP 422 Unprocessable Entity
 * response when the received data is valid JSON but does not follow a
 * business rule.
 *
 * URL:
 * http://localhost:8080/exercise17/students
 */

package com.angel.springbootlearning.exercises.exercise17;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentUnprocessableEntityController {

    @PostMapping("/exercise17/students")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {

        if (isBlank(request.name())) { return badRequest("Student name is mandatory!"); }
        if (isBlank(request.role())) { return badRequest("Student role must exist!"); }
        if (!isAllowed(request.role())) { return unprocessableEntity("Student role must be, frontend, backend of fullstack"); }

        StudentResponse response = new StudentResponse(
            1,
            request.name(),
            request.role()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }

    // Defines the allowed student roles
    private List<String> getAllowedRoles() { return List.of("backend", "frontend", "fullstack"); }

    private boolean isAllowed(String role) {
        return getAllowedRoles().stream()
            .anyMatch(allowedRole -> allowedRole.equalsIgnoreCase(role));
    }

    private ResponseEntity<ErrorResponse> badRequest(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<ErrorResponse> unprocessableEntity(String message) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    // The expected data from request body
    public record CreateStudentRequest(String name, String role) {}
    public record StudentResponse(int id, String name, String role) {}
    public record ErrorResponse(int status, String error, String message) {}
    
}

/*
$body = @{
    name = "Angel"
    role = "backend"
} | ConvertTo-Json

Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise17/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body $body
*/
