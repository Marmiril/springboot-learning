/*
 * Exercise 27 - Partial student update
 *
 * Purpose:
 * This exercise updates only the fields received in a PATCH request
 * while preserving the existing values of the other student fields.
 *
 * URL:
 * http://localhost:8080/exercise27/students/{id}
 */


package com.angel.springbootlearning.exercises.exercise27;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise27/students")
public class StudentPartialUpdateController {
    
    // Storing 
    private final List<Student> students = new ArrayList<>();

    // Initializes the in-memory list with sample students
    public StudentPartialUpdateController () {
        students.add(new Student(1, "Angel", "Love master and fullstack"));
        students.add(new Student(2, "Kratos", "God of War"));
    }

    // Handles PATCH requests sent to exercise27/students/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Student> partiallyUpdateStudent(
        @PathVariable int id,
        @RequestBody StudentUpdateRequest request) {

            //Iterates through the list using each student`s position
            for (int index = 0; index < students.size(); index++) {

                // Get the studen stored at the current position
                Student currentStudent = students.get(index);

                // Checks if matches
                if (currentStudent.id() == id) {

                    // Uses the received name or preserves the current one
                    String updatedName =
                        request.name() != null && !request.name().isBlank()
                            ? request.name()
                            : currentStudent.name();
                    
                    // Same in role
                    String updatedRole = 
                        request.role() != null && !request.role().isBlank()
                            ? request.role()
                            : currentStudent.role();
                    // Creates new student with the partially updated data
                    Student updatedStudent = new Student(
                        id, 
                        updatedName,
                        updatedRole
                    );

                    // Replaces the existing studen tin the list
                    students.set(index, updatedStudent);

                    // Returns the updated student with 200 OK
                    return ResponseEntity.ok(updatedStudent);
                }
            }

            // Return HTTP 404 NOT FOUND when the student does not exist
            return ResponseEntity.notFound().build();
        }
    

    public record Student(int id, String name, String role) {}
    public record StudentUpdateRequest(String name, String role) {}
}

/**
 $id = Read-Host "Introduce el ID del estudiante"
$name = Read-Host "Nuevo nombre (Enter para conservarlo)"
$role = Read-Host "Nuevo rol (Enter para conservarlo)"

$bodyData = @{}

if (-not [string]::IsNullOrWhiteSpace($name)) {
    $bodyData["name"] = $name
}

if (-not [string]::IsNullOrWhiteSpace($role)) {
    $bodyData["role"] = $role
}

$body = $bodyData | ConvertTo-Json

try {
    $student = Invoke-RestMethod `
        -Uri "http://localhost:8080/exercise27/students/$id" `
        -Method Patch `
        -ContentType "application/json" `
        -Body $body

    Write-Host "Student updated successfully:"
    $student
}
catch {
    $statusCode = $_.Exception.Response.StatusCode.value__

    if ($statusCode -eq 404) {
        Write-Host "Student not found."
    }
    else {
        Write-Host "Request failed with status code $statusCode."
    }
}
 */