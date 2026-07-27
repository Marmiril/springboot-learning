package com.angel.springbootlearning.exercises.exercise29;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise29")
public class ProjectDuplicateController {
    
    private List<Project> projects = new ArrayList<>();

    public int nextId = 1;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest request) {

        boolean duplicateExists = projects.stream().anyMatch(project -> project.title().equalsIgnoreCase(request.title()));

        if (duplicateExists) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("There is already a project with such title"));
        }

        Project createdProject = new Project(
            nextId++,
            request.title(),
            request.type()
        );

        projects.add(createdProject);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdProject);
    }

    @GetMapping
    public List<Project> getProjects() { return projects; }

    public record Project(int id, String title, String type) {}
    public record ProjectRequest(String title, String type) {}
    public record ErrorResponse(String message) {}
}
