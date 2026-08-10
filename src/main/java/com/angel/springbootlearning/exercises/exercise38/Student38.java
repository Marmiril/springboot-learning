/*
 * Exercise 38 - Complete layered flow
 *
 * Purpose:
 * Represents the student data managed through the controller,
 * service and repository layers.
 *
 * URLs:
 * http://localhost:8080/exercise38/students
 * http://localhost:8080/exercise38/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise38;

public record Student38 (
    int id,
    String name,
    String role) {    
}
