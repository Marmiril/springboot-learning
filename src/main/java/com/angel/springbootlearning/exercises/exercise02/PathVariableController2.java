package com.angel.springbootlearning.exercises.exercise02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathVariableController2 {
    @GetMapping("/exercise02/hello2/{name}")
    public String helloName(@PathVariable String name) {
        return "May Hell Be With Thou " + name;
    }    
}
