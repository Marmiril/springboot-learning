/*
 * Exercise 39 - Layered CRUD
 *
 * Purpose:
 * Applies validation and business rules for complete student CRUD operations.
 *
 * URLs:
 * http://localhost:8080/exercise39/students
 * http://localhost:8080/exercise39/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise39;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.angel.springbootlearning.exercises.exercise22.StudentCreateController.Student;

@Service
public class StudentService39 {

    private final StudentRepository39 studentRepository;
    private int nextId = 1;

    public StudentService39(StudentRepository39 studentRespository) {
        this.studentRepository = studentRespository;
    }

    public List<Student39> getStudents() {
        return studentRepository.findAll();
    }

    public Student39 getStudentById(int id) {
        return studentRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There is no student with id: " + id
            ));
    }
    
    public List<Student39> getStudentsByRole(String role) {
        validateRequiredField(role, "Role");

        List<Student39> students = studentRepository.findByRole(role);

        if(students.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There are no students with role: " + role
            );
        }
        return students;
    }

    public Student39 createStudent(StudentRequest39 request) {
        validateRequest(request);
        validateDuplicatedName(request.name(), null);

        Student39 student = new Student39(
            nextId,
            request.name().trim(),
            request.role().trim()
        );

        return studentRepository.save(student);
    }

    public Student39 updateStudent(int id, StudentRequest39 request) {
        getStudentById(id);
        validateRequest(request);
        validateDuplicatedName(request.name(), id);

        Student39 updatedStudent = new Student39(
            id, 
            request.name().trim(),
            request.role().trim()
        );
        
        return studentRepository
            .update(updatedStudent)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There is no student with id: " + id
            ));
    }

    public Student39 patchStudent(int id, StudentRequest39 request) {
        Student39 currentStudent = getStudentById(id);

        if (request == null || (request.name() == null && request.role() == null)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "At least one field must be provided"
            );
        }

        String updatedName = request.name() == null
            ? currentStudent.name()
            : request.name();

        String updatedRole = request.role() == null
            ? currentStudent.role()
            : request.role();

        validateRequiredField(updatedName, "Name");
        validateRequiredField(updatedRole, "Role");
        validateDuplicatedName(updatedName, id);
        
        
        Student39 updatedStudent = new Student39(
            id,
            updatedName.trim(),
            updatedRole.trim()
        );

        return studentRepository
            .update(updatedStudent)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There is no student with id: " + id
            ));
    }

    public void deleteStudent(int id) {
        if (!studentRepository.deleteById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There is no student with id: " + id
            );
        }
    }

    private void validateRequest(StudentRequest39 request) {
        if (request == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "The request body is required"
            );
        }

        validateRequiredField(request.name(), "Name");
        validateRequiredField(request.role(), "Role");
    }

    private void validateRequiredField(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                fieldName + " is required"
            );
        }
    }

    // Allows the current student to retain their own name during an update
    private void validateDuplicatedName(String name, Integer currentStudentId) {
        studentRepository.findByName(name)
            .filter(student -> currentStudentId == null || student.id() != currentStudentId)
            .ifPresent(student -> {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A student with this name already exists: " + name
                );
            });
    }


    
}
