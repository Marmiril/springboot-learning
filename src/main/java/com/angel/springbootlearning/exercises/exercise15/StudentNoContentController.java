/*
 * Exercise 15 - HTTP 204 No Content
 *
 * Purpose:
 * This exercise introduces how to return an HTTP 204 No Content response
 * when an operation is completed successfully but does not need to return
 * a response body.
 *
 * URL:
 * http://localhost:8080/exercise15/students/1
 */

package com.angel.springbootlearning.exercises.exercise15;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentNoContentController {
    
    // Handles DELETE requests and simulates deleting a student by id
    @DeleteMapping("/exercise15/students/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable int id) {

        if (!studentExists(id)) {
            return notFound("Student with id " + id + "was not found!");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Simulates stored students
    private List<StudentResponse> getStudents() {
        return List.of(
            new StudentResponse(1, "Angel", "backend"),
            new StudentResponse(2, "Angellotti", "frontkend"),
            new StudentResponse(3, "Angie", "stackfully")
        );
    }

    // Checks if a student exists by id
    private boolean studentExists(int id) {
        return getStudents().stream()
            .anyMatch(student -> student.id() == id);
    }

    // The famous reusable structure
    private ResponseEntity<ErrorResponse> notFound(String message) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    // Defines the simulated student model
    public record StudentResponse(int id, String name, String role) {}

    // Defines the structured error response model
    public record ErrorResponse(int status, String error, String message) {}    
}

/*
204
Invoke-WebRequest `
  -Uri "http://localhost:8080/exercise15/students/1" `
  -Method Delete `
  -UseBasicParsing
*/

/*
404
try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise15/students/99" `
      -Method Delete `
      -UseBasicParsing
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
*/
