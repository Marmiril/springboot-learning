/*
 * Exercise 05 - JSON response with PathVariable
 *
 * Purpose:
 * This exercise combines a dynamic URL value with a JSON response.
 * The controller captures an id from the URL and returns a Java object
 * that Spring Boot automatically converts into JSON.
 *
 * URL:
 * http://localhost:8080/exercise05/students/1
 */

package com.angel.springbootlearning.exercises.exercise05;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentByIdController {

    // Handles GET requests sent to /exercise05/students/{id}
    @GetMapping("/exercise05/students/{id}")
    public StudentResponse getStudentById(@PathVariable int id) {
        return new StudentResponse(id, "Student " + id, "Spring Booty learner");
    }
    
    // Defines the response model that Spring Boot will convert to JSON
    public record StudentResponse(int id, String name, String role) {
    }
}
