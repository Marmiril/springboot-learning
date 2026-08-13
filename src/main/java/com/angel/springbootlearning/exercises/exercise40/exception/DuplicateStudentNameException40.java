/*
 * Exercise 40 - Architecture Review
 * Purpose: Represent an error caused by a duplicated student name.
 * URL: http://localhost:8080/ex40/students
 */

package com.angel.springbootlearning.exercises.exercise40.exception;

public class DuplicateStudentNameException40 extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateStudentNameException40(String message) {
        super(message);
    }    
}
