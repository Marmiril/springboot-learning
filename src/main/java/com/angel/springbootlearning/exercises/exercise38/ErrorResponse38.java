/*
 * Exercise 38 - Complete layered flow
 *
 * Purpose:
 * Represents a consistent JSON response for API errors.
 *
 * URL:
 * http://localhost:8080/exercise38/students
 */

package com.angel.springbootlearning.exercises.exercise38;

public record ErrorResponse38 (
    int status,
    String message
) {}
