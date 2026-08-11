/*
 * Exercise 39 Bis - Advanced Layered CRUD
 *
 * Purpose:
 * Applies student business rules, validations
 * and existence checks.
 *
 * URLs:
 * http://localhost:8080/exercise39bis/students
 * http://localhost:8080/exercise39bis/students/{id}
 * http://localhost:8080/exercise39bis/students?name={name}
 * http://localhost:8080/exercise39bis/students?role={role}
 */

package com.angel.springbootlearning.exercises.ex39bis;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService39Bis {
    
    private final StudentRespository39Bis studentRepository;
    private int nextId = 1;

    public StudentService39Bis(StudentRespository39Bis studentRepository) { this.studentRepository = studentRepository; }

    public List<Student39Bis> getStudents() { return studentRepository.findAll(); }

    public Student39Bis getStudentById(int id) { return requireStudentById(id); }

    public Student39Bis getStudentByName(String name) {
        validateRequiredField(name, "Name");
        return requireStudentByName(name);
    }

    public List<Student39Bis> getStudentsByRole(String role) {
        validateRequiredField(role, "Role");
        return requireStudentByRole(role);
    }

    public Student39Bis createStudent(StudentRequest39Bis request) {
        validateRequest(request);
        validateUniqueName(request.name(), 0);
        
        Student39Bis student = new Student39Bis(
            nextId++,
            request.name().trim(),
            request.role().trim(),
            LocalDateTime.now()
        );
        return studentRepository.create(student);
    }

    public Student39Bis updateStudent(int id, StudentRequest39Bis request) {
        Student39Bis existingStudent = requireStudentById(id);

        validateRequest(request);
        validateRequiredField(request.name(), "Name");
        validateRequiredField(request.role(), "Role");
        validateUniqueName(request.name(), id);

        Student39Bis updatedStudent = new Student39Bis(
            existingStudent.id(),
            request.name().trim(),
            request.role().trim(),
            existingStudent.registrationDate()
        );

        return studentRepository
            .update(updatedStudent)
            .orElseThrow(() -> studentNotFoundById(id));
    }
   
    public Student39Bis patchStudent(int id, StudentRequest39Bis request) {
        Student39Bis existingStudent = requireStudentById(id);

        validateRequest(request);
        if(request.name() == null && request.role() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "At least one field must be provided"
            );
        }

        String updatedName = existingStudent.name();
        String updatedRole = existingStudent.role();

        if (request.name() != null) {
            validateRequiredField(request.name(), "Name");
            validateUniqueName(request.name(), id);
            updatedName = request.name().trim();
        }

        if (request.role() != null) {
            validateRequiredField(request.role(), "Role");
            updatedRole = request.role().trim();
        }

        Student39Bis updatedStudent = new Student39Bis(
            existingStudent.id(),
            updatedName,
            updatedRole,
            existingStudent.registrationDate()
        );

        return studentRepository
            .update(updatedStudent)
            .orElseThrow(() -> studentNotFoundById(id));
    }

    public Student39Bis deleteStudentById(int id) {
        Student39Bis student = requireStudentById(id);
        studentRepository.delelteById(id);
        return student;
    }

    public Student39Bis deleteStudentByName(String name) {
        validateRequiredField(name, "Name");
        Student39Bis student = requireStudentByName(name);
        studentRepository.deleteByName(name);
        return student;
    }

    public List<Student39Bis> deleteStudentsByRole(String role) {
        validateRequiredField(role, "Role");
        requireStudentByRole(role);

        return studentRepository.deleteByRole(role);
    }

    /////////////////////////////////////////////////////////////////////////////////

    private void validateRequest(StudentRequest39Bis request) {
        if (request == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Request body is required"
            );
        }
        validateRequiredField(request.name(), "Name");
        validateRequiredField(request.role(), "Role");
    }

    private void validateRequiredField(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                fieldName + " is required."
            );
        }
    }

    private void validateUniqueName(String name, int currentStudentId) {
        
        boolean duplicatedName = studentRepository
            .findByName(name)
            .filter(student -> student.id() != currentStudentId)
            .isPresent();
        
        if (duplicatedName) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "There is a student with name: " + name
            );
        }
    }

    private Student39Bis requireStudentById(int id) {
        return studentRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There is no student with id: " + id
            ));
    }

    private Student39Bis requireStudentByName(String name) {
        return studentRepository
            .findByName(name)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There is no student with name: " + name
            ));
    }

    private List<Student39Bis> requireStudentByRole(String role) {
        List<Student39Bis> students = studentRepository.findByRole(role);
        
        if (students.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There are not students with role " + role
            );
        }
        return students;
    }

    private ResponseStatusException studentNotFoundById(int id) {
        return new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "There is no student with id: " + id
        );
    }
}
