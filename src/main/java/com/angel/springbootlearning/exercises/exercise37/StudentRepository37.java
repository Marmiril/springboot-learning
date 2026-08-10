package com.angel.springbootlearning.exercises.exercise37;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository37 {
    
    private final List<Student37> students = new ArrayList<>();

    public List<Student37> findAll() { return new ArrayList<>(students); }

    public Student37 save(Student37 student) {
        students.add(student);
        return student;
    }
 
    // Optional stand before null if there is no id
    public Optional<Student37> findById(int id) {
        return students
            .stream()
            .filter(student -> student.id() == id)
            .findFirst();
    }
}
