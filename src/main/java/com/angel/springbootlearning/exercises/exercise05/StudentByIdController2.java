package com.angel.springbootlearning.exercises.exercise05;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentByIdController2 {
    @GetMapping("/exercise05/students2/{id}")
    public StudentResponse getStudentById(@PathVariable int id) {
        return new StudentResponse(id, "Warrior " + id, "God of War");
    }
    public record StudentResponse(int id, String name, String role) {}
}
