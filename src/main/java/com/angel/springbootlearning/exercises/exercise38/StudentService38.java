package com.angel.springbootlearning.exercises.exercise38;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService38 {
    
    private final StudentRepository38 studentRepository;

    private int nextId = 1;

    public StudentService38(StudentRepository38 studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student38> getStudents() {
        return studentRepository.findAll();
    }

    public Student38 getStudentById(int id) {
        return studentRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "There is no student with id: " + id
            ));
    }

    public List<Student38> getStudentByRole(String role) {
        List<Student38> students = studentRepository.findByRole(role);
        if (students.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
               "There is no student with role: " + role 
            );
        }
        return students;
    }

    public Student38 createStudent(StudentRequest38 request) {
        Student38 createdStudent = new Student38(
            nextId++,
            request.name(),
            request.role()
        );

        return studentRepository.save(createdStudent);
    }
}
