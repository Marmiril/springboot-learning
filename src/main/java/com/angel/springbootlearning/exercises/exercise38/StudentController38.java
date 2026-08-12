/*
 * Exercise 38 - Complete layered flow
 *
 * Purpose:
 * Exposes student operations through a complete layered architecture
 * and supports filtering students by role.
 *
 * URLs:
 * http://localhost:8080/exercise38/students
 * http://localhost:8080/exercise38/students?role={role}
 * http://localhost:8080/exercise38/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise38;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise38/students")
public class StudentController38 {

    private final StudentService38 studentService;

    public StudentController38(StudentService38 studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity <StudentListResponse38> getStudents(
        @RequestParam(required = false) String role) {
            List<Student38> students = role == null
                ? studentService.getStudents()
                : studentService.getStudentByRole(role);

            String message = students.isEmpty()
                ? "There are no students registered"
                : "Students retrieved successfully";

            StudentListResponse38 response =
                new StudentListResponse38(message, students);

            return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student38> getStudentById(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }



    @PostMapping
    public ResponseEntity<Student38> createStudent(
        @RequestBody StudentRequest38 request) {
            Student38 createdStudent = studentService.createStudent(request);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
        }
    
}
