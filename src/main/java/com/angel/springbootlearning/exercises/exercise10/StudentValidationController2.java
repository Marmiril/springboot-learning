package com.angel.springbootlearning.exercises.exercise10;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class StudentValidationController2 {
    
    @PostMapping("/exercise10/students2")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {
        
        if (request.name() == null || request.name().isBlank()) {
            return new ResponseEntity<>(
                new ErrorResponse("The student nº2 is required"),
                HttpStatus.BAD_REQUEST
            );
        }

        if (request.role() == null || request.role().isBlank()) {
            return new ResponseEntity<>(
                new ErrorResponse("Student2 role is required"),
                HttpStatus.BAD_REQUEST
            );
        }

        StudentResponse response = new StudentResponse(
            1,
            request.name(),
            request.role()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public record CreateStudentRequest(String name, String role) {}
    public record StudentResponse(int id, String name, String role) {}
    public record ErrorResponse(String message) {}
}
