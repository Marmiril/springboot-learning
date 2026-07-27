package com.angel.springbootlearning.exercises.exercise27;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise27/projects")
public class ProjectPartialUpdateController {

    private List<Project> projects = new ArrayList<>();

    public ProjectPartialUpdateController() {
        projects.add(new Project(1, "Philadelphia", "Top Secret"));
        projects.add(new Project(2, "Gobekli Tepe", "Proto-History"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Project> partiallyUpdateProject(
        @PathVariable int id, 
        @RequestBody ProjectUpdateRequest request) {

            for (int i = 0; i < projects.size(); i++) {
                
                Project currentProject = projects.get(i);

                if (currentProject.id() == id) {

                    String updatedTitle = 
                        request.title() != null && !request.title().isBlank()
                            ? request.title()
                            : currentProject.title();

                    String updatedType =
                        request.type() != null && !request.type().isBlank()
                            ? request.type()
                            : currentProject.type();
                    
                    Project updatedProject = new Project(
                        id, 
                        updatedTitle,
                        updatedType
                    );

                    projects.set(i, updatedProject);

                    return ResponseEntity.ok(updatedProject);
                }             
            }
                return ResponseEntity.notFound().build();               
        }   


    public record Project(int id, String title, String type) {}
    public record ProjectUpdateRequest(String title, String type) {}
}
