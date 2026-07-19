/*
 * Exercise 22 - Create a student
 *
 * Purpose:
 * This exercise receives a student as JSON through a POST request
 * and stores the received object temporarily in an ArrayList.
 *
 * URL:
 * http://localhost:8080/exercise22/students
 */

package com.angel.springbootlearning.exercises.exercise22;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Defines the base URL use by every endopoint in this controller
@RequestMapping("/exercise22/students")
public class StudentCreateController {

    // Stores the created students temporary while the application is running
    private final List<Student> students = new ArrayList<>();

    // Handles POST requests sent to /exercise22/students
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        // Ads the received student to the in-memory-list
        students.add(student);

        // Returns the created student with HTTP status 201 CREATED
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(student);
    }

    // Defines the student model received and returned as JSON
    public record Student(int id, String name, String role) {}    
}

/*
$body = @{
    id   = 1
    name = "Angel"
    role = "Backend developer"
} | ConvertTo-Json

Invoke-RestMethod `
    -Uri "http://localhost:8080/exercise22/students" `
    -Method Post `
    -ContentType "application/json" `
    -Body $body
*/
