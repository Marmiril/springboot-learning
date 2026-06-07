package com.angel.springbootlearning.exercises.exercise09;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentResponseEntityController2 {
    
    @PostMapping("/exercise09/students2")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest request) {

        StudentResponse response = new StudentResponse(
            1,
            request.name(),
            request.role()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    public record CreateStudentRequest(String name, String role) {}
    public record StudentResponse(int id, String name, String role) {}
}
