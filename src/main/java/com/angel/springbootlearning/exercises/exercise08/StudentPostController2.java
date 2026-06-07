package com.angel.springbootlearning.exercises.exercise08;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentPostController2 {
    @PostMapping("/exercise08/students2")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest request) {
        return new StudentResponse(1, request.name(), request.role());
    }
    
    public record CreateStudentRequest(String name, String role) {}
    public record StudentResponse(int id, String name, String role) {}
}
