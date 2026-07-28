/*
 * Exercise 33 - Constructor Injection
 *
 * Purpose:
 * This service centralizes student operations and is managed by Spring.
 *
 * URLs:
 * http://localhost:8080/exercise33/students
 */

package com.angel.springbootlearning.exercises.exercise33;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class StudentService3 {
    
    private final List<Student> students = new ArrayList<>(
        List.of(
            new Student(1, "Angel", "Backend"),
            new Student(2, "Lara", "Raider")
        )
    );

    private int nextId = 3;

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public Student createStudent(StudentRequest request) {
        Student createdStudent = new Student(
            nextId++,
            request.name(),
            request.role()
        );

        students.add(createdStudent);
        return createdStudent;
    }
}
