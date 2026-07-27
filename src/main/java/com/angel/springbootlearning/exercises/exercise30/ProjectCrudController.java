package com.angel.springbootlearning.exercises.exercise30;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProjectCrudController {

    private List<Project> projects = new ArrayList<>();

    private int nextIndex = 1;

    @GetMapping
    public List<Project> getProjects() { return projects; }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable int id) {
        int projectIndex = findProjectIndexById(id);
        if(projectIndex == -1) { return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("There is no project with such ID: " + id); }
        return ResponseEntity.ok(projects.get(projectIndex));
    }

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest request) {

        if (hasInvalidData(request)) {
            return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("Title & type are mandatory"));
        }

        if (titleAlreadyExists(request.title(), 0)) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("There is already a project with such name!"));
        }

        Project createdProject =  new Project(
            nextIndex++,
            request.title(),
            request.type()
        );

        projects.add(createdProject);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceProject(@PathVariable int id, @RequestBody ProjectRequest request) {

        int projectIndex = findProjectIndexById(id);
        if (projectIndex == -1) { return ResponseEntity.notFound().build(); }

        if (hasInvalidData(request)) {
            return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("Title and type are mandatory!"));
        }

        if (titleAlreadyExists(request.title(), id)) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("There is a project with such name!");
        }
        
        Project updatedProject = new Project(
            id,
            request.title(),
            request.type()
        );

        projects.set(projectIndex, updatedProject);

        return ResponseEntity.ok(updatedProject);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partiallyUpdateProject(@PathVariable int id, @RequestBody ProjectRequest request) {

        int projectIndex = findProjectIndexById(id);
        if(projectIndex == -1) { return ResponseEntity.notFound().build(); }

        Project currentProject = projects.get(projectIndex);

        String updatedTitle = 
            request.title() != null && !request.title().isBlank()
                ? request.title()
                : currentProject.title();

        String updatedType =
            request.type() != null && !request.title().isBlank()
                ? request.type()
                : currentProject.type();
                    
        if (titleAlreadyExists(updatedTitle, id)) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("There is a project with that title!");
        }

        Project updatedProject = new Project(
            id,
            updatedTitle, 
            updatedType
        );

        projects.set(projectIndex, updatedProject);

        return ResponseEntity.ok(updatedProject);   
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        int projectIndex = findProjectIndexById(id);
        if(projectIndex == -1) { return ResponseEntity.notFound().build(); }
        projects.remove(projectIndex);
        return ResponseEntity.noContent().build();
    }

    private int findProjectIndexById(int id) {
        for (int index = 0; index < projects.size(); index++) {
            if (projects.get(index).id == id) { return index; }
        }
        return -1;
    }

    private boolean hasInvalidData(ProjectRequest request) {

        return request.title() == null
        || request.type() == null
        || request.title().isBlank()
        || request.type().isBlank();
    }

    private boolean titleAlreadyExists(String title, int excludedId) {
        return projects.stream()
            .anyMatch(project -> project.id() != excludedId
            && project.title().equalsIgnoreCase(title));
    }


    public record Project(int id, String title, String type) {}
    public record ProjectRequest(String title, String type) {}
    public record ErrorResponse(String message) {}
    
}
