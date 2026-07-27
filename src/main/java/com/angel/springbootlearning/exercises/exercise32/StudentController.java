
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
public class StudentController {

    private final StudentService studentService = new StudentService();

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }
    
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student receivedStudent) {
        Student createStudent = studentService.createStudent(receivedStudent);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createStudent);
    }
}
 