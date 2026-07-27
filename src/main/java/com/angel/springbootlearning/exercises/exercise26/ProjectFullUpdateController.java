package com.angel.springbootlearning.exercises.exercise26;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise26/projects")
public class ProjectFullUpdateController {
    
    private List<Project> projects = new ArrayList<>();

    public ProjectFullUpdateController() {
        projects.add(new Project(1, "Kung fu", "Martial arts"));
        projects.add(new Project(2, "Scotish bagpipe", "Music"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
        @PathVariable int id,
        @RequestBody Project receivedProject) {

        for (int index = 0; index < projects.size(); index++) {
            Project currenProject = projects.get(index);

            if (currenProject.id() == id) {
                Project updatedProject = new Project(
                    id, receivedProject.title(),
                    receivedProject.type()
                );
                
                projects.set(index, updatedProject);

                return ResponseEntity.ok(updatedProject);
            }
        }
        return ResponseEntity.notFound().build();
    }

    public record Project(int id, String title, String type) {}
}
