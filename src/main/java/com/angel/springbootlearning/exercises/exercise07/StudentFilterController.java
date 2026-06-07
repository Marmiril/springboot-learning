/*
 * Exercise 07 - Optional query parameter filter
 *
 * Purpose:
 * This exercise introduces how to use an optional query parameter
 * to filter a JSON list response.
 *
 * URLs:
 * http://localhost:8080/exercise07/students
 * http://localhost:8080/exercise07/students?role=backend
 */

package com.angel.springbootlearning.exercises.exercise07;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentFilterController {

    // Handles GET request sent to /exercise07/students with an optional role filter
    @GetMapping("/exercise07/students")
    public List<StudentResponse> getStudents(@RequestParam(required = false) String role) {

        List<StudentResponse> students = List.of(
                new StudentResponse(1, "Angel", "backend"),
                new StudentResponse(2, "Angello", "frontend"),
                new StudentResponse(3, "Angie", "fullstack"),
                new StudentResponse(4, "Angie2", "fullstack"),
                new StudentResponse(5, "Angellotti", "love student"));

        if (role == null || role.isBlank()) {
            return students;
        }

        return students.stream()
                .filter(student -> student.role().equalsIgnoreCase(role))
                .toList();
    }

    // Defines the resposne model that Spring Boot will convert to JSON
    public record StudentResponse(int id, String name, String role) {
    }
}
