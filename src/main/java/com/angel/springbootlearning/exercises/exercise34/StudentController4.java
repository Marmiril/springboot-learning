/*
 * Exercise 34 - Simulated repository layer
 *
 * Purpose:
 * This exercise separates student storage from business logic
 * through a simulated repository layer.
 *
 * URL:
 * http://localhost:8080/exercise34/students
 */

package com.angel.springbootlearning.exercises.exercise34;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angel.springbootlearning.student.dto.StudentRequest;
import com.angel.springbootlearning.student.model.Student;

@RestController
@RequestMapping("/exercise34/students")
public class StudentController4 {   
    
    private final StudentService4 studentService;

    // Spring injects the service through the constructor
    public StudentController4(StudentService4 studentService) {
        this.studentService = studentService;
    }

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
$baseUrl = "http://localhost:8080/exercise34/students"

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

Write-Host "`n--- Updated student list ---"

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
