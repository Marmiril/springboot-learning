/*
 * Exercise 03 - Query parameter
 *
 * Purpose:
 * This exercise shows how to receive data through a query parameter.
 * Query parameters are commonly used for filters, searches and optional values.
 *
 * Example URL:
 * http://localhost:8080/exercise03/greet?name=Angel
 */

package com.angel.springbootlearning.exercises.exercise03;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestParamController {

    // Captures the value provided as a query parameter named "name"
    @GetMapping("/exercises03/greet")
    public String greet(@RequestParam String name) {
        return "Greetings, " + name;
    }    
}
