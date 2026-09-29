package com.angel.springbootlearning.exercises.exercise40Bis.service;

import java.security.PublicKey;
import java.util.List;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.exercises.exercise40Bis.dto.StudentRequest40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.exception.InvalidStudentRequestException40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.exception.StudentNotFoundException40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.model.Student40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.repository.StudentRepository40Bis;

@Service 
public class StudentService40Bis {
    
    private final StudentRepository40Bis studentRespository;

    private int nextId = 1;

    public StudentService40Bis(StudentRepository40Bis studentRepository) { this.studentRespository = studentRepository; }
    
    public List<Student40Bis> getStudents() { return studentRespository.findAll(); }
    public Student40Bis getStudentById(int id) { return requireStudentById(id); }
    public Student40Bis getStudentByName(String name) { return requireStudentByName(name); }
    public List<Student40Bis> getStudentsByRole(String role) { return requireStudentsByRole(role); }
    public Student40Bis createStudent(StudentRequest40Bis) {
        validateRequest(request);
    }





    ///////////////////////////////////////////////
    
    private Student40Bis requireStudentById(int id) {
        return studentRespository
            .findById(id)
            .orElseThrow(() -> studentNotFoundById(id));
    }

    private Student40Bis requireStudentByName(String name) {
        validateRequestField(name, "Name");
        return studentRespository
            .findByName(name)
            .orElseThrow(() -> new StudentNotFoundException40Bis(name));
    }

    private List<Student40Bis> requireStudentsByRole(String role) {
        validateRequestField(role, "Role");
        List<Student40Bis> students = studentRespository.findByRole(role);
        if (students.isEmpty()) { throw new StudentNotFoundException40Bis("There is no student with role: " + role); }
        return students;
    }

    private StudentNotFoundException40Bis studentNotFoundById(int id) { return new StudentNotFoundException40Bis("There is no student with such id: " + id); }
    private void validateRequestField(String value, String fieldName) {
        if (value == null || value.isBlank()) { throw new InvalidStudentRequestException40Bis(fieldName + " is required!"); }
    }
    private void validateRequest(StudentRequest40Bis request) {
        if (request == null) { throw new InvalidStudentRequestException40Bis("Request fields are empty..."); }
        validateRequestField(name, "Name");
        validateRequestField(role, "Role");
    }
}
