/*
 * Exercise 35 - Clean controller
 *
 * Purpose:
 * This exercise keeps business and persistence operations outside
 * the controller so it only handles HTTP requests and responses.
 *
 * URL:
 * http://localhost:8080/exercise35/students
 */

package com.angel.springbootlearning.exercises.exercise35;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.student.dto.StudentRequest;
import com.angel.springbootlearning.student.model.Student;
import com.angel.springbootlearning.student.repository.StudentRepository;

@Service
public class StudentService5 {

    private final StudentRepository studentRepository;
    
    private int nextId = 3;

    // Spring injects the reposity through
    public StudentService5(StudentRepository studentRepository) {
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
