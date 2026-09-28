package com.angel.springbootlearning.exercises.exercise40Bis.exception;

public class StudentNotFoundException40Bis extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public StudentNotFoundException40Bis(String message) { super(message); }
    
}
