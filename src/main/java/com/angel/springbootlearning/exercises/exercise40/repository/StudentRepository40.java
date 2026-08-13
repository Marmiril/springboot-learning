/*
 * Exercise 39 Bis - Advanced Layered CRUD
 *
 * Purpose:
 * Manages the in-memory persistence of students.
 *
 * URLs:
 * http://localhost:8080/exercise40/students
 * http://localhost:8080/exercise40/students/{id}
 */


package com.angel.springbootlearning.exercises.exercise40.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.angel.springbootlearning.exercises.exercise40.model.Student40;

public class StudentRepository40 {
        
    private final List<Student40> students = new ArrayList<>();

    public List<Student40> findAll() { return List.copyOf(students); }

    public Student40 create(Student40 student) {
        students.add(student);
        return student;
    }

    public Optional<Student40> findById(int id) {
        return students.stream()
            .filter(student -> student.id() == id)
            .findFirst();
    }

    public Optional<Student40> findByName(String name) {
        return students.stream()
            .filter(student -> student.name().equalsIgnoreCase(name))
            .findFirst();
    }

    public List<Student40> findByRole(String role) {
        return students.stream()
            .filter(student -> student.role().equalsIgnoreCase(role))
            .toList();
    }

    public Optional<Student40> update(Student40 updatedStudent) {
        for (int index = 0; index < students.size(); index++) {
            if(students.get(index).id() == updatedStudent.id()) {
                students.set(index, updatedStudent);

                return Optional.of(updatedStudent);
            }
        }
        
        return Optional.empty();
    }

    public Optional<Student40> deleteById(int id) {
        Optional<Student40> student = findById(id);

        student.ifPresent(students::remove);

        return student;
    }

    public Optional<Student40> deleteByName(String name) {
        Optional<Student40> student = findByName(name);

        student.ifPresent(students::remove);
        
        return student;
    }

    public List<Student40> deleteByRole(String role) {
        List<Student40> studentsToDelete = findByRole(role);
        
        students.removeAll(studentsToDelete);

        return studentsToDelete;
    }

}
