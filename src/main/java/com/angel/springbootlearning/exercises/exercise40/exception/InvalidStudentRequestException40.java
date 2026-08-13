/*
 * Exercise 40 - Architecture Review
 * Purpose: Represent an error caused by invalid student request data.
 * URL: http://localhost:8080/ex40/students
 */

package com.angel.springbootlearning.exercises.exercise40.exception;

public class InvalidStudentRequestException40 extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidStudentRequestException40(String message) {
        super(message);
    }    
}
