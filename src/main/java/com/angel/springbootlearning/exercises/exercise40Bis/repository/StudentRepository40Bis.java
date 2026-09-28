package com.angel.springbootlearning.exercises.exercise40Bis.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.angel.springbootlearning.exercises.exercise40Bis.Student40Bis;

@Repository 
public class StudentRepository40Bis {

    private final List<Student40Bis> students = new ArrayList<>();

    public List<Student40Bis> findAll() { return List.copyOf(students); }

    public Student40Bis create(Student40Bis student) {
        students.add(student);
        return student;
    }
    
    public Optional<Student40Bis> findById(int id) { return students.stream().filter(student -> student.id() == id).findFirst(); }
    public Optional<Student40Bis> findByName(String name) { return students.stream().filter(student -> student.name().equalsIgnoreCase(name)).findFirst(); }
    public List<Student40Bis> findByRole(String role) { return students.stream().filter(student -> student.role().equalsIgnoreCase(role)).toList(); }

    public Optional<Student40Bis> update(Student40Bis updatedStudent) {
        for (int index = 0; index < students.size(); index++) {
            if (students.get(index).id() == updatedStudent.id()) {
                students.set(index, updatedStudent);
                return Optional.of(updatedStudent);
            }
        }
        return Optional.empty();
    }

}

