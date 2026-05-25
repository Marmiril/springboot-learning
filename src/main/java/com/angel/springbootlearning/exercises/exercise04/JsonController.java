/*
 * Exercise 04 - JSON response
 *
 * Purpose:
 * This exercise introduces how Spring Boot can return structured data as JSON.
 * Instead of returning plain text, the controller returns a Java object that
 * Spring Boot automatically converts into a JSON response.
 *
 * URL:
 * http://localhost:8080/exercise04/student
 */

package com.angel.springbootlearning.exercises.exercise04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {
    // Handles GET requests sent to /exercise04/student
    @GetMapping("/exercise04/student")
    public StudentResponse getStudent() {
        return new StudentResponse(1, "Angel", "Spring Boot student");
    }

    // Defines the response model that Spring Boot will convert to JSON
    public record StudentResponse(int id, String name, String role){
        
    }
}
