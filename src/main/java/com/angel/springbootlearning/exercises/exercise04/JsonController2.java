package com.angel.springbootlearning.exercises.exercise04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController2 {
    @GetMapping("/exercise04/student2")
    public StudentResponse getStudent() {
        return new StudentResponse(666, "Doom guy", "Doom Slayer");        
    }

    public record StudentResponse(int id, String name, String role) {}
    
}
