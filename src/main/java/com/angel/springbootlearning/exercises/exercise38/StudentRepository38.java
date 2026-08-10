package com.angel.springbootlearning.exercises.exercise38;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository38 {
    
    private final List<Student38> students = new ArrayList<>();

    public List<Student38> findAll() { return new ArrayList<>(students); }

    public Student38 save(Student38 student) {
        students.add(student);
        return student;
    }

    // Searches for a student without returning null
    public Optional<Student38> findById(int id) {
        return students
            .stream()
            .filter(student -> student.id() == id)
            .findFirst();
    }

    // Returns students whose role matches, ignoring uppercase and lowercase
    public List<Student38> findByRole(String role) {
        return students
            .stream()
            .filter(student -> student.role().equalsIgnoreCase(role.trim()))
            .toList();
    }
}
