/*
 * Exercise 40 - Architecture Review
 * Purpose: Represent an error caused by a student that cannot be found.
 * URL: http://localhost:8080/ex40/students
 */ 

package com.angel.springbootlearning.exercises.exercise40.exception;

public class StudentNotFoundException40 extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public StudentNotFoundException40(String message) {
        super(message);
    }
}
