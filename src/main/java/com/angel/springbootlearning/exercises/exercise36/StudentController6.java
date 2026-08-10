/*
 * Exercise 36 - Service validation
 *
 * Purpose:
 * This exercise delegates student validation and creation
 * to the service layer.
 *
 * URL:
 * http://localhost:8080/exercise36/students
 */

package com.angel.springbootlearning.exercises.exercise36;

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
@RequestMapping("/exercise36/students")
public class StudentController6 {

    private final StudentService6 studentService;

    public StudentController6(StudentService6 studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(
        @RequestBody StudentRequest request) {
            Student createdStudent = studentService.createStudent(request);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
        }        
}
