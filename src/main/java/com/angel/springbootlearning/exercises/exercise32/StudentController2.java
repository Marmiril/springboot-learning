
/*
 * Exercise 32 - Create StudentService
 *
 * Purpose:
 * This exercise delegates student operations to StudentService.
 *
 * URLs:
 * http://localhost:8080/exercise32/students
 */

package com.angel.springbootlearning.exercises.exercise32;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise32/students")
public class StudentController2 {

    private final StudentService2 studentService = new StudentService2();

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }
    
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequest receivedStudent) {
        Student createdStudent = studentService.createStudent(receivedStudent);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdStudent);
    }
}
 
/*
$baseUrl = "http://localhost:8080/exercise32/students"

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