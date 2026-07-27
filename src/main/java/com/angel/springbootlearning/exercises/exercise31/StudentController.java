package com.angel.springbootlearning.exercises.exercise31;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise31/students")
public class StudentController {

    private final StudentService studentService = new StudentService();

    // Delegates the operation instead of managing the student data directlyy
    @GetMapping
    public List<String> getStudents() { return studentService.getStudents(); }

}
