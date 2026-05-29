package com.angel.springbootlearning.exercises.exercise01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController2 {
    @GetMapping("/exercise01/hello2")
    public String hello2(){
        return "Hell again, Springy Boot with devilTools";
    }
}
