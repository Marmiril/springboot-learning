/*
 * Exercise 32 - Create StudentService
 *
 * Purpose:
 * This exercise uses a StudentService to centralize student operations.
 *
 * URLs:
 * http://localhost:8080/exercise32/students
 */

package com.angel.springbootlearning.exercises.exercise32;

public record Student (  
    int id,
    String name,
    String role    
){}

