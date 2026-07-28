/*
 * Exercise 34 - Simulated repository layer
 *
 * Purpose:
 * This exercise stores students in memory and provides
 * persistence operations to StudentService4.
 *
 * URL:
 * http://localhost:8080/exercise34/students
 */

package com.angel.springbootlearning.exercises.exercise34;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.angel.springbootlearning.student.model.Student;

@Repository
public class StudentRepository {
    
    private final List<Student> students = new ArrayList<>(
        List.of(
            new Student(1, "Angel", "Backend"),
            new Student(2, "Doom guy", "Doom slayer")
        )
    );

    // Returns a copy to prevent external modification of the stored list
    public List<Student> findAll() { return new ArrayList<>(students); }

    // Stores and returns the received student
    public Student save(Student student) {
        students.add(student);
        return student;
    }
}
