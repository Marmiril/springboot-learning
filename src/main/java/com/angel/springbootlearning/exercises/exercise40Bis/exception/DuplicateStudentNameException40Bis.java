package com.angel.springbootlearning.exercises.exercise40Bis.exception;

public class DuplicateStudentNameException40Bis extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateStudentNameException40Bis(String message) {
        super(message);
    }
    
}
