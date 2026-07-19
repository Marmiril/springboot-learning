/*
 * Exercise 21 - Mutable in-memory list
 *
 * Purpose:
 * This exercise introduces an ArrayList as temporary mutable storage.
 * The stored students exist only while the application is running.
 *
 * URL:
 * http://localhost:8080/exercise21/students
 */

package com.angel.springbootlearning.exercises.exercise21;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/exercise21/students")
public class StudentListContoller {

    // Mutable in-memory storage
    private final List<String> students = new ArrayList<>();

    // Initializes the controller with sample students
    public StudentListContoller() {
        students.add("Ángel");
        students.add("Kratos");
    }

    // Handles GET requests sent to /exercise21/students
    @GetMapping
    public List<String> getStudents() {
        return students;
    }
}
 /*
 curl.exe -s "http://localhost:8080/exercise21/students"
 */