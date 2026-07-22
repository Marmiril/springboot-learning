package com.angel.springbootlearning.exercises.exercise22;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise22/projects")
public class ProjectCreateController {

    private final List<Project> projects = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        projects.add(project);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(project);
    }

    public record Project(int id, String title, String type){}
    
}
