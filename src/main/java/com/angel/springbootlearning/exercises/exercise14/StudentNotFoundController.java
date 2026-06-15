/*
 * Exercise 14 - HTTP 404 Not Found
 *
 * Purpose:
 * This exercise introduces how to return an HTTP 404 Not Found response
 * when a requested resource does not exist.
 *
 * URL:
 * http://localhost:8080/exercise14/students/1
 */

package com.angel.springbootlearning.exercises.exercise14;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentNotFoundController {

    @GetMapping("/exercise14/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {

        StudentResponse student = findStudentById(id);

        if (student == null) {
            return notFound("Student with id: " + id + " was not found!");
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // Simulated stored students
    private List<StudentResponse> getStudents() {
        return List.of(
            new StudentResponse(1, "Ángel", "backend"),
            new StudentResponse(2, "Angello", "frontend"),
            new StudentResponse(1, "Ángelove", "sullftack")
        );
    }

    // Searches for student by id
    private StudentResponse findStudentById(int id) {
        return getStudents().stream()
            .filter(student -> student.id() == id)
            .findFirst()
            .orElse(null);
    }
    
    // The reusable structured not found response
    private ResponseEntity<ErrorResponse> notFound(String message) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    public record StudentResponse(int id, String name, String role) {}
    public record ErrorResponse(int status, String error, String message) {}

}
/*
http://localhost:8080/exercise14/students/1

http://localhost:8080/exercise14/students/99
*/