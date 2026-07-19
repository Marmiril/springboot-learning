/*
 * Exercise 25 - Delete a student by id
 *
 * Purpose:
 * This exercise deletes a student from an in-memory ArrayList
 * using the id received as a path variable.
 *
 * URL:
 * http://localhost:8080/exercise25/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise25;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise25/students")
public class StudentDeleteController {
    
    private final List<Student> students = new ArrayList<>();

    // Initializes the in-memory list with sample students
    public StudentDeleteController() {

        // Samples 
        students.add(new Student(1, "Angel", "Backend"));
        students.add(new Student(2, "Kratos", "God of War"));
    }

    // Handles DELETE requests sent to /exercise25/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {

        // Removes the student whose id matches the requested id
        boolean removed = students.removeIf(student -> student.id() == id);

        // Returns HTTP 204 NO CONTENT when the student was removed
        if (removed) {
            return ResponseEntity.noContent().build();
        }

        // HTTP 404 NOT FOUND if doesnt exist
        return ResponseEntity.notFound().build();
    }

    public record Student(int id, String name, String role) {}
}


/*
$response = Invoke-WebRequest `
    -Uri "http://localhost:8080/exercise25/students/1" `
    -Method Delete `
    -UseBasicParsing

$response.StatusCode
*/

/*
try {
    Invoke-WebRequest `
        -Uri "http://localhost:8080/exercise25/students/99" `
        -Method Delete `
        -UseBasicParsing
}
catch {
    $_.Exception.Response.StatusCode.value__
}
*/