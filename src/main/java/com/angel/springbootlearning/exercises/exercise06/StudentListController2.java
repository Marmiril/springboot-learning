package com.angel.springbootlearning.exercises.exercise06;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StudentListController2 {
    @GetMapping("/exercise06/students2")
    public List<StudentResponse> getStudents() {
        return List.of(
            new StudentResponse(1, "Kratos", "God Of War"),
            new StudentResponse(2, "Doomguy", "Doom Slayer"),
            new StudentResponse(3, "The Duke", "Master in Power and Love")
        );
    }

    public record StudentResponse(int id, String name, String role) {}
    

    
}

