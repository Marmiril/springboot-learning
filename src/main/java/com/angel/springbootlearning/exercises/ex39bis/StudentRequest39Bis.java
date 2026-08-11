/*
 * Exercise 39 Bis - Advanced Layered CRUD
 *
 * Purpose:
 * Represents the editable student data received from the user.
 *
 * URLs:
 * http://localhost:8080/exercise39bis/students
 * http://localhost:8080/exercise39bis/students/{id}
 */

package com.angel.springbootlearning.exercises.ex39bis;

public record StudentRequest39Bis(
    String name,
    String role)
{}
