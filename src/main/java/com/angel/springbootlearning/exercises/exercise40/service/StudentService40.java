/*
 * Exercise 40 - Architecture Review
 *
 * Purpose:
 * Applies student business rules, validations
 * and existence checks without depending on HTTP.
 *
 * URLs:
 * http://localhost:8080/exercise40/students
 * http://localhost:8080/exercise40/students/{id}
 * http://localhost:8080/exercise40/students?name={name}
 * http://localhost:8080/exercise40/students?role={role}
 */

package com.angel.springbootlearning.exercises.exercise40.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.angel.springbootlearning.exercises.exercise40.dto.StudentRequest40;
import com.angel.springbootlearning.exercises.exercise40.exception.DuplicateStudentNameException40;
import com.angel.springbootlearning.exercises.exercise40.exception.InvalidStudentRequestException40;
import com.angel.springbootlearning.exercises.exercise40.exception.StudentNotFoundException40;
import com.angel.springbootlearning.exercises.exercise40.model.Student40;
import com.angel.springbootlearning.exercises.exercise40.repository.StudentRepository40;

@Service
public class StudentService40 {
    
    private final StudentRepository40 studentRepository;

    private int nextId = 1;

    public StudentService40(StudentRepository40 studentRepository) { this.studentRepository = studentRepository; }

    public List<Student40> getStudents() { return studentRepository.findAll(); }

    public Student40 getStudentById(int id) { return requireStudentById(id); }

    public Student40 getStudentByName(String name) { return requireStudentByName(name); }

    public List<Student40> getStudentByRole(String role) { return requireStudentsByRole(role);}

    public Student40 createStudent(StudentRequest40 request) {
        validateRequest(request);
        validateUniqueName(request.name(), 0);
        
        Student40 student = new Student40(
            nextId,
            request.name().trim(),
            request.role().trim(),
            LocalDateTime.now(ZoneId.of("Europe/Madrid"))
        );

        return studentRepository.create(student); }

    public Student40 updateStudent(int id, StudentRequest40 request) {
        Student40 student = requireStudentById(id);

        validateRequest(request);
        validateUniqueName(request.name(), id);

        Student40 updatedStudent = new Student40(
            student.id(),
            request.name(),
            request.role(),
            student.registrationDate()
        );

        return studentRepository
            .update(student)
            .orElseThrow(() -> studentNotFoundById(id)
        );

    }

    /////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////


    private void validateRequest(StudentRequest40 request) {
        if (request == null)  {
            throw new InvalidStudentRequestException40("Request fields are empty.");
        }
        validateRequestField(request.name(), "Name");
        validateRequestField(request.role(), "Role");        
    }

    private void validateRequestField(String value, String fieldName) {

        if (value ==  null || value.isBlank()) {
            throw new InvalidStudentRequestException40(fieldName + " is required!");
        }
    }

    private void validateUniqueName(String name, int currentId) {

        name = name.trim();

        boolean duplicated =  studentRepository
            .findByName(name)
            .filter(student -> student.id() != currentId)
            .isPresent();

        if (duplicated) { throw new DuplicateStudentNameException40 ("There is a student with name: " + name); }
    }

    private Student40 requireStudentById(int id) {
        return studentRepository
            .findById(id)
            .orElseThrow(() -> studentNotFoundById(id));
    }

    private Student40 requireStudentByName(String name) {
        return studentRepository
            .findByName(name)
            .orElseThrow(() -> new StudentNotFoundException40(name));
    }

    private List<Student40> requireStudentsByRole(String role) {
        List<Student40> students = studentRepository.findByRole(role);

        if (students.isEmpty()) {
            throw new StudentNotFoundException40("There are not students with role: " + role);
        }
        
        return students;
    }

    private StudentNotFoundException40 studentNotFoundById(int id) { throw new StudentNotFoundException40("There is no student with id: " + id);}

}
