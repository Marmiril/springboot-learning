package com.angel.springbootlearning.exercises.exercise39;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRespository39 {

    private final List<Student39> students = new ArrayList<>();

    public List<Student39> findAll() { return new ArrayList<>(students); }

    public Student39 save(Student39 student) { 
        students.add(student);
        return student;
    }

    public Optional<Student39> findById(int id) {
        return students
            .stream()
            .filter(student -> student.id() == id)
            .findFirst();
    }

    public Optional<Student39> fingByName(String name) {
        return students
            .stream()
            .filter(student -> student.name().equalsIgnoreCase(name.trim()))
            .findFirst();
    }

    public boolean existsName(String name) {
        return students
            .stream()
            .anyMatch(student -> student.name().equalsIgnoreCase(name.trim()));
    }

    public List<Student39> findByRole(String role) {
        return students
            .stream()
            .filter(student -> student.role().equalsIgnoreCase(role.trim()))
            .toList();
    }
}
