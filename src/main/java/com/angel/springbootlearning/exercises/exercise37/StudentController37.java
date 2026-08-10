/*
 * Exercise 37 - In-memory repository
 *
 * Purpose:
 * Exposes HTTP operations and delegates student management
 * to the service and repository layers.
 *
 * URLs:
 * http://localhost:8080/exercise37/students
 * http://localhost:8080/exercise37/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise37;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise37/students")
public class StudentController37 {
    
    private final StudentService37 studentService;

    public StudentController37(StudentService37 studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student37>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student37> getStudentById(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student37> createStudent(
        @RequestBody StudentRequest37 request) {
            Student37 createdStudent = studentService.createStudent(request);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
        }

}
