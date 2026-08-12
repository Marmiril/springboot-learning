/*
 * Exercise 39 Bis - Advanced Layered CRUD
 *
 * Purpose:
 * Exposes the HTTP endpoints for managing students.
 *
 * URLs:
 * http://localhost:8080/exercise39bis/students
 * http://localhost:8080/exercise39bis/students/{id}
 * http://localhost:8080/exercise39bis/students?name={name}
 * http://localhost:8080/exercise39bis/students?role={role}
 */

package com.angel.springbootlearning.exercises.ex39bis;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angel.springbootlearning.exercises.ex39bis.StudentClassResponse39Bis.StudentDeletionListResponse39Bis;
import com.angel.springbootlearning.exercises.ex39bis.StudentClassResponse39Bis.StudentListResponse39Bis;
import com.angel.springbootlearning.exercises.ex39bis.StudentClassResponse39Bis.StudentResponse39Bis;

@RestController
@RequestMapping("/exercise39bis/students")
public class StudentController39Bis {
    
    private final StudentService39Bis studentService;

    public StudentController39Bis(StudentService39Bis studentService)  { 
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<StudentListResponse39Bis> getStudents(
        @RequestParam(required = false) String role        
    ) {
        List<Student39Bis> students;
        String message;

        if(role != null) {
            students = studentService.getStudentsByRole(role);
            message = "Students with role: " + role + " retrieved successfully";
        } else {
            students = studentService.getStudents();
            message = students.isEmpty()
                ? "There are no students registered yet"
                : "Students retrieved successfully";
        }

        StudentListResponse39Bis response = 
            new StudentListResponse39Bis(message, students);
            
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse39Bis> getStudentById(@PathVariable int id) {
        Student39Bis student = studentService.getStudentById(id);

        StudentResponse39Bis response =
            new StudentResponse39Bis(
                "Student with id: " + id + " retrieved successfully",
                student
            );

        return ResponseEntity.ok(response);
    }


    @GetMapping(params = "name")
    public ResponseEntity<StudentResponse39Bis> getStudentByName(@RequestParam String name) {
        Student39Bis student = studentService.getStudentByName(name);

        StudentResponse39Bis response = new StudentResponse39Bis(
            "Student with name: " + name + " retrieved successfully",
            student
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StudentResponse39Bis> createStudent(@RequestBody StudentRequest39Bis request) {
        Student39Bis createdStudent = studentService.createStudent(request);

        StudentResponse39Bis response =
            new StudentResponse39Bis(
            "Student created successfully",
            createdStudent
            );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);        
    }

    @PutMapping("/{id}") 
    public ResponseEntity<StudentResponse39Bis> updateStudent(
        @PathVariable int id,
        @RequestBody StudentRequest39Bis request
    ) {
        Student39Bis updatedStudent = studentService.updateStudent(id, request);

        StudentResponse39Bis response = new StudentResponse39Bis(
            "Student with id: " + id + " updated successfully",
            updatedStudent
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponse39Bis> patchStudent(
        @PathVariable int id,
        @RequestBody StudentRequest39Bis request
    ) {
        Student39Bis patchedStudent = studentService.patchStudent(id, request);

        StudentResponse39Bis response = new StudentResponse39Bis(
            "Student with id: " + id + " partially updated successfully.",
            patchedStudent
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponse39Bis> deleteStudentById(@PathVariable int id) {
        Student39Bis deletedStudent = studentService.deleteStudentById(id);

        StudentResponse39Bis response = new StudentResponse39Bis(
            "Student with id: " + id + " deleted successfully",
            deletedStudent
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<StudentResponse39Bis> deleteStudentByName(@RequestParam String name) {
        Student39Bis deletedStudent = studentService.deleteStudentByName(name);

        StudentResponse39Bis response = new StudentResponse39Bis(
            "Student with name: " + name + " deleted successfully",
            deletedStudent
        );

        return ResponseEntity.ok(response);

    }

    @DeleteMapping(params = "role")
    public ResponseEntity<StudentDeletionListResponse39Bis> deleteStudentsByRole(@RequestParam String role) {
        List<Student39Bis> deletedStudents = studentService.deleteStudentsByRole(role);

        StudentDeletionListResponse39Bis response = new StudentDeletionListResponse39Bis(
            "Students deleted with role: " + role + " successfully",
            deletedStudents.size(),
            deletedStudents
        );

        return ResponseEntity.ok(response);
    }

}
