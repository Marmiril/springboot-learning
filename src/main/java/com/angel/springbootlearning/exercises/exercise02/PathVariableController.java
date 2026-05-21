/*
 * Exercise 02 - Path variable
 *
 * Purpose:
 * This exercise shows how to capture a dynamic value from the URL path.
 * The value provided after /hello/ is received as a Java method parameter.
 *
 * Example URL:
 * http://localhost:8080/exercise02/hello/Angel
 */

package com.angel.springbootlearning.exercises.exercise02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathVariableController {

    // Captures the value provided in the UEL after /excercise02/hello
    @GetMapping("/exercise02/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hell, " + name;
    }
}
