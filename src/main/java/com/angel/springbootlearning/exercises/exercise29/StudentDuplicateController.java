/*
 * Exercise 29 - Prevent duplicate students
 *
 * Purpose:
 * This exercise prevents creating students with duplicate names
 * by checking the existing in-memory list before saving.
 *
 * URLs:
 * POST http://localhost:8080/exercise29/students
 * GET  http://localhost:8080/exercise29/students
 */


package com.angel.springbootlearning.exercises.exercise29;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise29/students")
public class StudentDuplicateController {
    
    // Storing
    private final List<Student> students = new ArrayList<>();

    // Stored index 
    private int nextId = 1;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest request) {
        
        // Checks if the student`s name already exists
        boolean duplicateExists = students.stream().anyMatch(student -> student.name().equalsIgnoreCase(request.name()));

        // Returns conflict
        if (duplicateExists) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("A student with this name already exists"));
        }

        // Creates the student using the automatically generated id
        Student createdStudent = new Student(
            nextId++,
            request.name(),
            request.role()
        );


        // Stores the new student in memory
        students.add(createdStudent);

        // Returns the student using with HTTP 201 CREATED
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdStudent);
    }

    // Handles GET requests sent to /exercise29/students
    @GetMapping
    public List<Student> getStudents() { return students; }

    public record Student(int id, String name, String role) {}
    public record StudentRequest(String name, String role) {}
    public record ErrorResponse(String error) {}
}

/*
 --- CREATE STUDENT ---
$name = Read-Host "Enter the student name"
$role = Read-Host "Enter the student role"

$body = @{
    name = $name
    role = $role
} | ConvertTo-Json

try {
    $student = Invoke-RestMethod `
        -Uri "http://localhost:8080/exercise29/students" `
        -Method Post `
        -ContentType "application/json" `
        -Body $body

    Write-Host "Student created successfully:"
    $student | Format-List
}
catch {
    $statusCode = [int]$_.Exception.Response.StatusCode

    if ($statusCode -eq 409) {
        Write-Host "A student with this name already exists."
    }
    else {
        Write-Host "Request failed with status code $statusCode."
    }
}
*/

/*
--- LIST STUDENTS --- 
try {
    $students = Invoke-RestMethod `
        -Uri "http://localhost:8080/exercise29/students" `
        -Method Get

    Write-Host "Stored students:"
    $students | Format-Table
}
catch {
    $statusCode = [int]$_.Exception.Response.StatusCode
    Write-Host "Request failed with status code $statusCode."
}
*/
