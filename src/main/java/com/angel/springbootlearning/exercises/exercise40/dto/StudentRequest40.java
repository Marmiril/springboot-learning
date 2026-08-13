/*
 * Exercise 39 Bis - Advanced Layered CRUD
 *
 * Purpose:
 * Represents the editable student data received from the user.
 *
 * URLs:
 * http://localhost:8080/exercise40/students
 * http://localhost:8080/exercise40/students/{id}
 */


package com.angel.springbootlearning.exercises.exercise40.dto;

public record StudentRequest40 (
    String name,
    String role
) {}
