package com.angel.springbootlearning.exercises.exercise40Bis.exception;

public class InvalidStudentRequestException40Bis extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidStudentRequestException40Bis(String message) {
        super(message);
    }
    
}
