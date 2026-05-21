/*
 * Exercise 01 - Basic REST endpoint
 *
 * Purpose:
 * This exercise introduces the basic structure of a Spring Boot REST controller.
 * It creates a fixed endpoint that returns a plain text response.
 *
 * URL:
 * http://localhost:8080/exercise01/hello
 */

package com.angel.springbootlearning.exercises.exercise01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Handles GET requests sent to /exercise01/hello
    @GetMapping("/exercises01/hello")
    public String hello() {
        return "Hell, Spring Booty";
    }    
}
