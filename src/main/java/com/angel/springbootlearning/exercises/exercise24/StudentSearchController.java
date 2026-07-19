/*
 * Exercise 24 - Find a student by id
 *
 * Purpose:
 * This exercise searches for a specific student inside an in-memory ArrayList
 * using the id received as a path variable.
 *
 * URL:
 * http://localhost:8080/exercise24/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise24;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Marks this class as a REST controller
@RestController
// Defines the base URL shared by every endpoint in this controller
@RequestMapping("/exercise24/students")
public class StudentSearchController {

    // Storing
    private final List<Student> students = new ArrayList<>();

    // Initializes the in-memory list with sample students
    public StudentSearchController() {

        // Adds samples
        students.add(new Student(1, "Angel", "Backend"));
        students.add(new Student(2, "Kratos", "God of War"));
    }

    // Handles GET requests sent to /exercise24/students{id}
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {

        // Iterates through every student stored in memory
        for (Student student : students) {

            // Checks whether the current student's id matches ethe requested id
            if (student.id() == id) {

                // Return the matching student with HTTP response 200 OK
                return ResponseEntity.ok(student);
            }
        }

        // Returns HTTP status 404 NOT FOUND when no student matches the id
        return ResponseEntity.notFound().build();
    }

    public record Student(int id, String name, String role) {}
}
