/*
 * Exercise 39 - Layered CRUD
 *
 * Purpose:
 * Exposes complete CRUD operations using
 * controller, service and repository layers.
 *
 * URLs:
 * http://localhost:8080/exercise39/students
 * http://localhost:8080/exercise39/students?role={role}
 * http://localhost:8080/exercise39/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise39;

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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/exercise39/students")
public class StudentController39 {
    
    private final StudentService39 studentService;

    public StudentController39(StudentService39 studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity <StudentListResponse39> getStudents(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String role) {

            // Only one param search is allowed per request
            if (name != null && role != null) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Only one search param can be used: name or role!"
                );
            }

            List<Student39> students;

            if(name != null) {
                students = List.of(
                    studentService.getStudentByName(name)
                );
            } else if (role != null) {
                students = studentService.getStudentsByRole(role);
            } else {
                students = studentService.getStudents();
            }

            String message = name != null 
                ? "Student with name " + name + " retreived successfully."
                : role != null
                    ? "Students with role " + role + " retreived successfully."
                    : students.isEmpty()
                        ? "There are not students registered yet."
                        : "Students retreived successfully.";

            StudentListResponse39 response = new StudentListResponse39(message, students);

            return ResponseEntity.ok(response);
        }
 
    @GetMapping("/{id}")    
    public ResponseEntity<Student39> getStudentById(@PathVariable int id) {
        Student39 student = (studentService.getStudentById(id));
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student39> createStudent(@RequestBody StudentRequest39 request) {
        Student39 createdStudent = studentService.createStudent(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student39> updateStudent(
        @PathVariable int id,
        @RequestBody StudentRequest39 request
    ) {

        Student39 updatedStudent = studentService.updateStudent(id, request);

        return ResponseEntity.ok(updatedStudent);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student39> patchStudent(
        @PathVariable int id,
        @RequestBody StudentRequest39 request
    ) {
        Student39 patchedStudent = studentService.patchStudent(id, request);
        return ResponseEntity.ok(patchedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse39> deleteStudent(
        @PathVariable int id
    ) {
        Student39 deletedStudent = studentService.deleteStudent(id);

        DeleteResponse39 response = new DeleteResponse39(
            "Student deleted successfully",
            deletedStudent
        );
        return ResponseEntity.ok(response);
    }
    
}
