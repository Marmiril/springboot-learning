package com.angel.springbootlearning.exercises.exercise07;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentFilterController2 {
    @GetMapping("/exercise07/students2")
    public List<StudentResponse> getStudents(@RequestParam(required = false) String role)
    {
        List<StudentResponse> students = List.of(
          new StudentResponse(1, "Doomguy", "Doom slayer"), 
          new StudentResponse(2, "Kratos", "God of War"), 
          new StudentResponse(3, "Dante", "Doom slayer"),
          new StudentResponse(4, "The Duke", "Master of weaponry and love")
        );

        if (role == null || role.isBlank()) { return students; }
        
        return students.stream()
            .filter(student -> student.role().equalsIgnoreCase(role))
            .toList();
    }

    public record StudentResponse(int id, String name, String role) {}
}
