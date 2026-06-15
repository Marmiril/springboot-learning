/*
 * Exercise 16 - HTTP 409 Conflict
 *
 * Purpose:
 * This exercise introduces how to return an HTTP 409 Conflict response
 * when the request is valid but conflicts with existing data.
 *
 * URL:
 * http://localhost:8080/exercise16/students
 */

package com.angel.springbootlearning.exercises.exercise16;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentConflictController {

    @PostMapping("/exercise16/students")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {
        
        if (isBlank(request.name())) { return badRequest("Student name is required!"); } 
        if (isBlank(request.role())) { return badRequest("Student role must exists!"); }
        if (studentNameExists(request.name())) { return conflict("Student name already exists!"); }

        StudentResponse response = new StudentResponse(
            4,
            request.name(),
            request.role()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Simulates existing stored students
    private List<StudentResponse> getExistingStudents() {
        return List.of(
          new StudentResponse(1, "Ángel", "blackend"),
          new StudentResponse(2, "Jimmy", "frontend"),
          new StudentResponse(3, "Angellotti", "tuttiend")
        );
    }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }

    // Checks if the received student name already exists
    private boolean studentNameExists(String name) {
        return getExistingStudents().stream()
            .anyMatch(student -> student.name().equalsIgnoreCase(name));
    }

    // The reusable structure for badRequest my boy!
    private ResponseEntity<ErrorResponse> badRequest(String message) {
        HttpStatus  status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    // Builds a reusalbe strcutured conflict response
    private ResponseEntity<ErrorResponse> conflict(String message) {
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    public record CreateStudentRequest(String name, String role) {}
    public record StudentResponse(int id, String name, String role) {}
    public record ErrorResponse(int status, String error, String message) 
    {}

    
}
