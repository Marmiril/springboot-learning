/*
 * Exercise 06 - JSON list response
 *
 * Purpose:
 * This exercise introduces how Spring Boot can return a list of Java objects
 * as a JSON array. This is a common pattern in REST APIs when returning
 * multiple resources, such as users, products, orders or students.
 *
 * URL:
 * http://localhost:8080/exercise06/students
 */

package com.angel.springbootlearning.exercises.exercise06;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentListController {

    // Handles GET requests sent to /exercise06/students
    @GetMapping("/exercise06/students")
    public  List<StudentResponse> getStudents() {
        return List.of(
            new StudentResponse(1, "Angel", "Spring Booty student"),
            new StudentResponse(2, "Angello", "Java student"),
            new StudentResponse(3, "Angellotti dil Coure", "Love student")
        );
    }

    // Defines the response model that Spring Boot will convert to JSON VOORHEES
    public record StudentResponse(int id, String name, String role){}
}
