package com.angel.springbootlearning.exercises.exercise25;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise25/projects")
public class ProjectDeleteController {
    
    private List<Project> projects = new ArrayList<>();

    public ProjectDeleteController() {
        projects.add(new Project(1, "Vivaldo", "Music"));
        projects.add(new Project(2,"Karak-Urum", "Geography"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        boolean removed = projects.removeIf(project -> project.id() == id);

        if (removed) { return ResponseEntity.noContent().build(); }

        return ResponseEntity.notFound().build();
    }

    public record Project(int id, String title, String type) {}
}
