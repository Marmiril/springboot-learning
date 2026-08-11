/*
 * Exercise 39 Bis - Advanced Layered CRUD
 *
 * Purpose:
 * Represents a student stored in the virtual database.
 *
 * URLs:
 * http://localhost:8080/exercise39bis/students
 * http://localhost:8080/exercise39bis/students/{id}
 */

package com.angel.springbootlearning.exercises.ex39bis;

import java.time.LocalDateTime;

public record Student39Bis(
    int id, 
    String name,
    String role,
    LocalDateTime registrationTime
) {}
