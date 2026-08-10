/*
 * Exercise 37 - In-memory repository
 *
 * Purpose:
 * Uses a repository to manage student data stored in memory.
 *
 * URLs:
 * http://localhost:8080/exercise37/students
 * http://localhost:8080/exercise37/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise37;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService37 {
    
    private final StudentRepository37 studentRepository;
    private int nextId = 1;

    public StudentService37(StudentRepository37 studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student37> getStudents() {
        return studentRepository.findAll();
    }

    public Student37 getStudentById(int id) {
        return studentRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There is no student with id: " + id
            ));
    }

    public Student37 createStudent(StudentRequest37 request) {
        Student37 createdStudent = new Student37(
            nextId++,
            request.name(),
            request.role()
        );

        return studentRepository.save(createdStudent);
    }


}
