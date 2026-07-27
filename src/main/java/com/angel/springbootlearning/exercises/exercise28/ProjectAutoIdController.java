package com.angel.springbootlearning.exercises.exercise28;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise28/projects")
public class ProjectAutoIdController {

    private List<Project> projects = new ArrayList<>();

    private int nextId = 1;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectRequest request) {
        
        int generatedId = nextId++;

        Project createdProject = new Project(
            generatedId,
            request.title(),
            request.type()
        );

        projects.add(createdProject);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdProject);
    }

    public record Project(int id, String title, String type) {}
    public record ProjectRequest(String title, String type) {}
}
