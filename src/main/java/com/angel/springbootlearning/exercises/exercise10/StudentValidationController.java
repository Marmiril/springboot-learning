/*
 * Exercise 10 - Basic validation with POST
 *
 * Purpose:
 * This exercise introduces a basic manual validation before creating
 * a response. If the received JSON contains invalid data, the controller
 * returns an HTTP 400 Bad Request response.
 *
 * URL:
 * http://localhost:8080/exercise10/students
 */

package com.angel.springbootlearning.exercises.exercise10;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class StudentValidationController {
 
    // Handles POST requests and validates the received JSON data
    @PostMapping("/exercise10/students")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {

        if (request.name() == null || request.name().isBlank()) {
            return new ResponseEntity<>(
                new ErrorResponse("The student name is required"),
                HttpStatus.BAD_REQUEST
            );
        }

        if (request.role() == null || request.role().isBlank()) {
            return new ResponseEntity<>(
                new ErrorResponse("The student role is required"),
                HttpStatus.BAD_REQUEST
            );
        }

        StudentResponse response = new StudentResponse(
            1,
            request.name(),
            request.role()
        );
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Defines the data expected from the request body
    public record CreateStudentRequest(String name, String role) {}

    // Defines the successful responde model
    public record StudentResponse(int id, String name, String role) {}

    // Defines the error response model
    public record ErrorResponse(String message) {}
}
/*

Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise10/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"name":"Angel","role":"backend"}'
  
  **************************************************************************

  Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise10/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"name":"","role":"backend"}'
  
  
  */