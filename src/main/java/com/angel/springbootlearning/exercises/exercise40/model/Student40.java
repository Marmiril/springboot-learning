/*
 * Exercise 40 - Advanced Layered CRUD
 *
 * Purpose:
 * Represents a student stored in the virtual database.
 *
 * URLs:
 * http://localhost:8080/exercise40/students
 * http://localhost:8080/exercise40/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise40.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record Student40 (
    int id,
    String name,
    String role,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime registrationDate
) {}
