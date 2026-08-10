/*
 * Exercise 36 - Service validation
 *
 * Purpose:
 * This exercise moves business validation rules into the service
 * before storing a new student.
 *
 * URL:
 * http://localhost:8080/exercise36/students
 */


package com.angel.springbootlearning.exercises.exercise36;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.angel.springbootlearning.student.dto.StudentRequest;
import com.angel.springbootlearning.student.model.Student;
import com.angel.springbootlearning.student.repository.StudentRepository;

@Service
public class StudentService6 {
 
    private final StudentRepository studentRepository;
    private int nextId = 3;

    public StudentService6(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(StudentRequest request) {
        validateRequest(request);
        validateDuplicateName(request.name());

        Student createdStudent = new Student(
            nextId,
            request.name(),
            request.role()
        );

        return studentRepository.save(createdStudent);
    }

    // Validates the mandatory student data
    private void validateRequest(StudentRequest request) {
        if (request.name() == null || request.name().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Student name is required"
            );
        }

        if (request.role() == null || request.role().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Student role is required"
            );
        }
    }

    // Prevents duplicate student names regardless of uppercase or lowercase.
    private void validateDuplicateName(String name) {
        boolean duplicateExists = studentRepository
            .findAll()
            .stream()
            .anyMatch(student -> student.name().equalsIgnoreCase(name.trim()));

        if (duplicateExists) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "The student name already exists"
            );
        }
    }
    
}
