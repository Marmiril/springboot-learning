/*
 * Exercise 39 Bis - Advanced Layered CRUD
 *
 * Purpose:
 * Represents a personalized response for operations
 * affecting a single student, list or deletion.
 *
 * URLs:
 * http://localhost:8080/exercise39bis/students
 * http://localhost:8080/exercise39bis/students/{id}
 */

package com.angel.springbootlearning.exercises.ex39bis;

import java.util.List;

public class StudentClassResponse39Bis {

private StudentClassResponse39Bis() {}

public record StudentResponse39Bis(
    String message,
    Student39Bis student
) {}

public record StudentListResponse39Bis (
    String message,
    List<Student39Bis> students
) {}

public record StudentDeletionListResponse39Bis(
    String message,
    int deletedCount,
    List<Student39Bis> students
) {}

}
