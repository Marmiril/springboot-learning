package com.angel.springbootlearning.exercises.exercise03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestParamController2 {
    @GetMapping("/exercise03/greet2")
    public String greet2(@RequestParam String name) {
        return "Grrrreeeeetings, " + name;
    }
}
