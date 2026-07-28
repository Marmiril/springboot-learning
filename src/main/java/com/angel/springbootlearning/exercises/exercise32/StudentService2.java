/*
 * Exercise 32 - Create StudentService
 *
 * Purpose:
 * This exercise centralizes student operations inside a service class.
 *
 * URLs:
 * http://localhost:8080/exercise32/students
 */ 

package com.angel.springbootlearning.exercises.exercise32;

import java.util.ArrayList;
import java.util.List;

public class StudentService2 {
    
    private final List<Student> students = new ArrayList<>(
        List.of(
            new Student(1, "Ángel", "Backend"),
            new Student(2, "Kratos", "God of War")
        )
    );

    private int nextId = 3; 

    // Returns a copy to prevent external classes from modifying the stored list
    public List<Student> getStudents() { return new ArrayList<>(students); }

    // Creates the stored student and assigns its definitive ID
    public Student createStudent(StudentRequest receivedStudent) {
        Student createdStudent = new Student(
            nextId++,
            receivedStudent.name(),
            receivedStudent.role()
        );

        students.add(createdStudent);
        return createdStudent;
    }
}
