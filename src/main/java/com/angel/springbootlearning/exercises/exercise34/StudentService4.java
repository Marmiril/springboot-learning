/*
 * Exercise 34 - Simulated repository layer
 *
 * Purpose:
 * This exercise separates student storage from business logic
 * by delegating persistence operations to StudentRepository4.
 *
 * URLs:
 * http://localhost:8080/exercise34/students
 */

package com.angel.springbootlearning.exercises.exercise34;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.student.dto.StudentRequest;
import com.angel.springbootlearning.student.model.Student;
import com.angel.springbootlearning.student.repository.StudentRepository;

@Service
public class StudentService4 {
    
    private final StudentRepository studentRepository;
    private int nextId = 3;

    // Spring injects the repository through the constructor
    public StudentService4(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // Creates the student and delegates its storage to the repository
    public Student createStudent(StudentRequest request) {
        Student createdStudent = new Student(
            nextId++,
            request.name(),
            request.role()
        );

        return studentRepository.save(createdStudent);
    }
}

