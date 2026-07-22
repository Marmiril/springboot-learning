package com.angel.springbootlearning.exercises.exercise24;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise24/projects")
public class ProjectSearchController {

    private List<Project> projects = new ArrayList<>();

    public ProjectSearchController() {
        projects.add(new Project(1, "Vivaldi", "Music"));
        projects.add(new Project(2, "Karak-Urum", "Geography"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        for (Project project : projects) {
            if (project.id() == id) { return ResponseEntity.ok(project); }
        }
        return ResponseEntity.notFound().build();
    }

    public record Project(int id, String title, String type) {}

    
}
