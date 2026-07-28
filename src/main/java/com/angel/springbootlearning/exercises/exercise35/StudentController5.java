/*
 * Exercise 35 - Clean controller
 *
 * Purpose:
 * This exercise keeps the controller focused exclusively
 * on handling HTTP requests and responses.
 *
 * URL:
 * http://localhost:8080/exercise35/students
 */

package com.angel.springbootlearning.exercises.exercise35;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angel.springbootlearning.student.model.Student;

@RestController
@RequestMapping("/exercise23/students")
public class StudentController5 {

    private final StudentService5 studentService;

    public StudentController5(StudentService5 studentService) {
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
