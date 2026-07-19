
/*
 * Exercise 23 - List students
 *
 * Purpose:
 * This exercise stores students temporarily in an ArrayList
 * and returns all stored students through a GET request.
 *
 * URL:
 * http://localhost:8080/exercise23/students
 */

package com.angel.springbootlearning.exercises.exercise23;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Marks this class as a REST c controller
@RestController

// Defines the base URL shared by all endpoints in this controller
@RequestMapping("/exercise23/students")
public class StudentListController23 {

    // Storing while app is runnig
    private final List<Student> students = new ArrayList<>();

    // Handles POST requests sent to exercise23/students
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        // Adds the received student to the in-memory list
        students.add(student);

        // Returns the created student with HTTP status 201 CREATED
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(student);        
    }

    // Handles GET requests sent to /exercise23/students
    @GetMapping
    public List<Student> getStudents() { 

        // Returns every student currently stored in memory
        return students;
    }

    public record Student(int id, String name, String role) {}    
}

/*
$students = @(
    @{
        id   = 1
        name = "Angel"
        role = "Backend developer"
    },
    @{
        id   = 2
        name = "Kratos"
        role = "God of War"
    }
)

$students | ForEach-Object {
    Invoke-RestMethod `
        -Uri "http://localhost:8080/exercise23/students" `
        -Method Post `
        -ContentType "application/json" `
        -Body ($_ | ConvertTo-Json)
}
*/
