/*
 * Exercise 08 - Receive JSON with POST
 *
 * Purpose:
 * This exercise introduces how Spring Boot can receive JSON data
 * in the request body and convert it into a Java object.
 *
 * URL:
 * http://localhost:8080/exercise08/students
 */

package com.angel.springbootlearning.exercises.exercise08;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentPostController {

    // Handles POST requests sent to /exercise08/students
    @PostMapping("/exercise08/students")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest request) {
        return new StudentResponse(1, request.name(), request.role());
    }

    // Defines the data expected from the request body
    public record CreateStudentRequest(String name, String role) {}

    // Defines the response model that Spring Boot will convert to JSON
    public record StudentResponse(int id, String name, String role) {}
    
}

/*
Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise08/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"name":"Angel","role":"backend"}'
  
*/