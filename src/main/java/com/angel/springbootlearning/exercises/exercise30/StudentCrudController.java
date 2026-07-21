/*
 * Exercise 30 - Complete student CRUD
 *
 * Purpose:
 * This exercise integrates GET, POST, PUT, PATCH and DELETE
 * operations into a complete in-memory CRUD.
 *
 * URLs:
 * GET    http://localhost:8080/exercise30/students
 * GET    http://localhost:8080/exercise30/students/{id}
 * POST   http://localhost:8080/exercise30/students
 * PUT    http://localhost:8080/exercise30/students/{id}
 * PATCH  http://localhost:8080/exercise30/students/{id}
 * DELETE http://localhost:8080/exercise30/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise30;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise30/students")
public class StudentCrudController {

    // Storing
    private final List<Student> students = new ArrayList<>();

    // Next available
    private int nextId = 1;

    // Returns every stored student
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    // Returns one student by Id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        int studentIndex = findStudentIndexById(id);

        if (studentIndex == -1) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(students.get(studentIndex));
    }

    // Creates new student
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest request) {

        if (hasInvalidData(request)) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Name and role are mandatory!"));
        }

        if (nameAlreadyExists(request.name(), 0)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("A student with such name already exists!!"));
        }

        Student createdStudent = new Student(
                nextId++,
                request.name(),
                request.role());

        students.add(createdStudent);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    // Completely replaces an existing student
    @PutMapping("/{id}")
    public ResponseEntity<?> replaceStudent(
            @PathVariable int id,
            @RequestBody StudentRequest request) {

        int studentIndex = findStudentIndexById(id);

        if (studentIndex == -1) {
            return ResponseEntity.notFound().build();
        }

        if (hasInvalidData(request)) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Name and role are required!"));
        }
        if (nameAlreadyExists(request.name(), id)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Such name already exists!"));
        }

        Student updatedStudent = new Student(
                id,
                request.name(),
                request.role());

        students.set(studentIndex, updatedStudent);

        return ResponseEntity.ok(updatedStudent);
    }

    // Updates only the received fields
    @PatchMapping("/{id}")
    public ResponseEntity<?> partiallyUpdateStudent(
        @PathVariable int id,
        @RequestBody StudentPatchRequest request) {

            int studentIndex = findStudentIndexById(id);

            if (studentIndex == -1) { return ResponseEntity.notFound().build(); }
            
            Student currentStudent = students.get(studentIndex);

            String updatedName = 
                request.name() != null && !request.name().isBlank()
                    ? request.name()
                    : currentStudent.name();
            
            String updatedRole = 
                request.role() != null && !request.role().isBlank()
                    ? request.role()
                    : currentStudent.role();

            if (nameAlreadyExists(updatedName, id)) {
                return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ErrorResponse("A student with such name already exists!"));
            }

            Student updatedStudent = new Student(
                id, 
                updatedName,
                updatedRole 
            );

            students.set(studentIndex, updatedStudent);

            return ResponseEntity.ok(updatedStudent);
        }
    
    // Deletes student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        int studentIndex = findStudentIndexById(id);

        if (studentIndex == -1) { return ResponseEntity.notFound().build(); }

        students.remove(studentIndex);
        return ResponseEntity.noContent().build();
    }

    // Returns the student`s list position or -1 when does not exist
    private int findStudentIndexById(int id) {
        for (int index = 0; index < students.size(); index++) {
            if (students.get(index).id() == id) {
                return index;
            }
        }
        return -1;
    }

    private boolean hasInvalidData(StudentRequest request) {
        return request.name() == null
                || request.name().isBlank()
                || request.role() == null
                || request.role().isBlank();
    }

    private boolean nameAlreadyExists(String name, int excludedId) {
        return students.stream()
                .anyMatch(student -> student.id() != excludedId
                        && student.name().equalsIgnoreCase(name));
    }

    public record Student(int id, String name, String role) {
    }

    public record StudentRequest(String name, String role) {
    }

    public record StudentPatchRequest(String name, String role) {
    }

    public record ErrorResponse(String message) {
    }
}

/*
$baseUrl = "http://localhost:8080/exercise30/students"

$action = Read-Host "Choose an action: list, find, create, replace, update or delete"

try {
    switch ($action.ToLower()) {

        "list" {
            $students = Invoke-RestMethod `
                -Uri $baseUrl `
                -Method Get

            Write-Host "Stored students:"
            $students | Format-Table
        }

        "find" {
            $id = Read-Host "Enter the student id"

            $student = Invoke-RestMethod `
                -Uri "$baseUrl/$id" `
                -Method Get

            Write-Host "Student found:"
            $student | Format-List
        }

        "create" {
            $name = Read-Host "Enter the student name"
            $role = Read-Host "Enter the student role"

            $body = @{
                name = $name
                role = $role
            } | ConvertTo-Json

            $student = Invoke-RestMethod `
                -Uri $baseUrl `
                -Method Post `
                -ContentType "application/json" `
                -Body $body

            Write-Host "Student created:"
            $student | Format-List
        }

        "replace" {
            $id = Read-Host "Enter the student id"
            $name = Read-Host "Enter the new student name"
            $role = Read-Host "Enter the new student role"

            $body = @{
                name = $name
                role = $role
            } | ConvertTo-Json

            $student = Invoke-RestMethod `
                -Uri "$baseUrl/$id" `
                -Method Put `
                -ContentType "application/json" `
                -Body $body

            Write-Host "Student replaced:"
            $student | Format-List
        }

        "update" {
            $id = Read-Host "Enter the student id"
            $name = Read-Host "Enter the new name or press Enter to preserve it"
            $role = Read-Host "Enter the new role or press Enter to preserve it"

            $bodyData = @{}

            if (-not [string]::IsNullOrWhiteSpace($name)) {
                $bodyData["name"] = $name
            }

            if (-not [string]::IsNullOrWhiteSpace($role)) {
                $bodyData["role"] = $role
            }

            $body = $bodyData | ConvertTo-Json

            $student = Invoke-RestMethod `
                -Uri "$baseUrl/$id" `
                -Method Patch `
                -ContentType "application/json" `
                -Body $body

            Write-Host "Student updated:"
            $student | Format-List
        }

        "delete" {
            $id = Read-Host "Enter the student id"

            Invoke-RestMethod `
                -Uri "$baseUrl/$id" `
                -Method Delete

            Write-Host "Student deleted successfully."
        }

        default {
            Write-Host "Invalid action."
        }
    }
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
