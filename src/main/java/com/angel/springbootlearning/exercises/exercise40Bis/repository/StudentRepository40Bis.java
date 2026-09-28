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
    
    public Optional<Student40Bis> findById(int id) { return students.stream().filter(student -> student.id() == id).findFirst(); 

    
    }
}
