package com.angel.springbootlearning.exercises.exercise21;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise21/projects")
public class ProjectListController {

    private final List<String> projects = new ArrayList<>();

    public ProjectListController() {
        projects.add("Karak-Urum");
        projects.add("Vivaldi");
    }

    @GetMapping
    public List<String> getProjects() { return projects; }
}
