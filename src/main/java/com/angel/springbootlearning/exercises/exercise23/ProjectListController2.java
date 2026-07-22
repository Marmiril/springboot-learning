package com.angel.springbootlearning.exercises.exercise23;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise23/projects")
public class ProjectListController2 {
    
    public final List<String> projects = new ArrayList<>();

    public ProjectListController2() {
        projects.add("Music");
        projects.add("WW2");
    }

    @GetMapping
    public List<String> getProjects() { return projects; }
    
}
