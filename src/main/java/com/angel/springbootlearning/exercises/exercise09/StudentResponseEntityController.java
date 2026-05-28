/*
 * Exercise 09 - ResponseEntity with POST
 *
 * Purpose:
 * This exercise introduces how to return an HTTP status code together
 * with a JSON response. Instead of returning only the object, the controller
 * returns a ResponseEntity to control the response more explicitly.
 *
 * URL:
 * http://localhost:8080/exercise09/students
 */

package com.angel.springbootlearning.exercises.exercise09;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentResponseEntityController {

    // Handles POST request and returns a JSON response with HTTP status 201
    @PostMapping("/exercise09/students")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest request) {

        StudentResponse response = new StudentResponse( 
            1,
            request.name(),
            request.role()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Defines the data expected from the request body
    public record CreateStudentRequest(String name, String role) {}
    
    // Defines the response model that Spring Boot will convert to JSON
    public record StudentResponse(int id, String name, String role) {}
}
