/*
 * Exercise 33 - Constructor Injection
 *
 * Purpose:
 * This exercise introduces constructor injection between the controller
 * and the service.
 *
 * URLs:
 * http://localhost:8080/exercise33/students
 */

package com.angel.springbootlearning.exercises.exercise33;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise33/students")
public class StudentController3 {

    private final StudentService3 studentService;

    // Spring injects the StudentService3 instance through this constructor
    public StudentController3(StudentService3 studentService) { this.studentService = studentService; }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping
   public ResponseEntity<Student> createStudent(@RequestBody StudentRequest request) {
        Student createdStudent = studentService.createStudent(request);
        return ResponseEntity
            .status(HttpStatus.CREATED) 
            .body(createdStudent);
   }   
}

/*
$baseUrl = "http://localhost:8080/exercise33/students"

Write-Host "`n--- Current students ---"

try {
    $students = Invoke-RestMethod `
        -Uri $baseUrl `
        -Method Get

    $students | Format-Table
} catch {
    Write-Host "Error getting students."
    Write-Host "HTTP status:" $_.Exception.Response.StatusCode.value_
}

Write-Host "`n--- Create student ---"

$name = Read-Host "Enter the student name"
$role = Read-Host "Enter the student role"

$body = @{
    name = $name
    role = $role
} | ConvertTo-Json

try {
    $createdStudent = Invoke-RestMethod `
        -Uri $baseUrl `
        -Method Post `
        -ContentType "application/json" `
        -Body $body

    Write-Host "Student created successfully:"
    $createdStudent | Format-List
} catch {
    Write-Host "Error creating student."
    Write-Host "HTTP status:" $_.Exception.Response.StatusCode.value_
}

Write-Host "`n--- Updated students ---"

try {
    $students = Invoke-RestMethod `
        -Uri $baseUrl `
        -Method Get

    $students | Format-Table
} catch {
    Write-Host "Error getting students."
    Write-Host "HTTP status:" $_.Exception.Response.StatusCode.value_
}
*/
 