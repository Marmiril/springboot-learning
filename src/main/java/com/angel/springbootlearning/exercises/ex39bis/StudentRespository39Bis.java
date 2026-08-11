/*
 * Exercise 39 Bis - Advanced Layered CRUD
 *
 * Purpose:
 * Manages the in-memory persistence of students.
 *
 * URLs:
 * http://localhost:8080/exercise39bis/students
 * http://localhost:8080/exercise39bis/students/{id}
 */

package com.angel.springbootlearning.exercises.ex39bis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.exercises.exercise22.StudentCreateController.Student;

@Service
public class StudentRespository39Bis {
    
    private final List<Student39Bis> students = new ArrayList<>();

    public List<Student39Bis> findAll() { return new ArrayList<>(students); }

    public Student39Bis create(Student39Bis student) {
        students.add(student);
        return student;
    }

    public Optional<Student39Bis> findById(int id) {
        return students.stream()
            .filter(student -> student.id() == id)
            .findFirst();
    }

    public Optional<Student39Bis> findByName(String name) {
        return students.stream()
            .filter(student -> student.name().equalsIgnoreCase(name))
            .findFirst();
    }

    public List<Student39Bis> findByRole(String role) {
        return students.stream()
            .filter(student -> student.role().equalsIgnoreCase(role))
            .toList();
    }

    public Optional<Student39Bis> update(Student39Bis updatedStudent) {
        for (int index = 0; index < students.size()) {
            if (students.get(index).id() == updatedStudent.id()) {
                students.set(index, updatedStudent);

                return Optional.of(updatedStudent);
            }
        }

        return Optional.empty();
    }

    public Optional<Student39Bis> delelteById(int id) {
        Optional<Student39Bis> student = findById(id);

        student.ifPresent(students::remove);

        return student;
    }

    public Optional<Student39Bis> deleteByName(String name) {
        Optional<Student39Bis> student = findByName(name);

        student.ifPresent(students::remove);

        return student;
    }

    public List<Student39Bis> deleteByRole(String role) {
        List<Student39Bis> studentsToDelete = findByRole(role);

        students.removeAll(studentsToDelete);
        return studentsToDelete;
    }

}
