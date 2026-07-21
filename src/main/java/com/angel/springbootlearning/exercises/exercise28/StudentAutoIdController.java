/*
 * Exercise 28 - Automatic student id
 *
 * Purpose:
 * This exercise generates student identifiers automatically
 * instead of receiving the id from the client.
 *
 * URLs:
 * POST http://localhost:8080/exercise28/students
 * GET  http://localhost:8080/exercise28/students
 */

package com.angel.springbootlearning.exercises.exercise28;

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
@RequestMapping("/exercise28/students")
public class StudentAutoIdController {

    // The storing
    private final List<Student> students = new ArrayList<>();
    

    // Stores the next available student identifier
    private int nextId = 1;

    // Handles the POST requests sent to /exercise28/students
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequest request) {

        // Uses the current id and then increases it for the next student
        int generatedId = nextId++;

        // Creates the student using the automatically generated id
        Student createStudent = new Student(
            generatedId,
            request.name(),
            request.role()
        );

        students.add(createStudent);

        // Adds the created student with HTTP 201 CREATED
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createStudent);
    }

    // Handles GET requests sent to /exercise28/students
    @GetMapping
    public List<Student> getStudents(){ return students; }



    public record Student(int id, String name, String role) {}
    public record StudentRequest (String name, String role) {}
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
        -Uri "http://localhost:8080/exercise28/students" `
        -Method Post `
        -ContentType "application/json" `
        -Body $body

    Write-Host "Student created successfully:"
    $student | Format-List
}
catch {
    if ($_.Exception.Response) {
        $statusCode = [int]$_.Exception.Response.StatusCode
        Write-Host "Request failed with status code $statusCode."
    }
    else {
        Write-Host $_.Exception.Message
    }
}
*/

/*
 --- LIST STUDENTS ---
try {
    $students = Invoke-RestMethod `
        -Uri "http://localhost:8080/exercise28/students" `
        -Method Get

    Write-Host "Stored students:"
    $students | Format-Table
}
catch {
    if ($_.Exception.Response) {
        $statusCode = [int]$_.Exception.Response.StatusCode
        Write-Host "Request failed with status code $statusCode."
    }
    else {
        Write-Host $_.Exception.Message
    }
}
*/