/*
 * Exercise 39 - Layered CRUD
 *
 * Purpose:
 * Manages student data in memory and provides the operations
 * required by the complete CRUD.
 *
 * URLs:
 * http://localhost:8080/exercise39/students
 * http://localhost:8080/exercise39/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise39;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository39 {

    private final List<Student39> students = new ArrayList<>();

    public List<Student39> findAll() {
        return new ArrayList<>(students);
    }

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

    public Optional<Student39> findByName(String name) {
        return students
                .stream()
                .filter(student -> student.name().equalsIgnoreCase(name.trim()))
                .findFirst();
    }

    public List<Student39> findByRole(String role) {
        return students
                .stream()
                .filter(student -> student.role().equalsIgnoreCase(role.trim()))
                .toList();
    }

    public boolean existsByName(String name) {
        return students
                .stream()
                .anyMatch(student -> student.name().equalsIgnoreCase(name.trim()));
    }

    public boolean deleteById(int id) {
        return students.removeIf(student -> student.id() == id);
    }

    // Replaces the stored student and returns it if the id exists
    public Optional<Student39> update(Student39 updatedStudent) {
        for (int index = 0; index < students.size(); index++) {
            if (students.get(index).id() == updatedStudent.id()) {
                students.set(index, updatedStudent);
                return Optional.of(updatedStudent);
            }
        }
        return Optional.empty();
    }
}
