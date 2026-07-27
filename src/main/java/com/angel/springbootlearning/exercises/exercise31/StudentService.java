/*
 * Exercise 31 - Separate Controller and Service
 *
 * Purpose:
 * This exercise separates the HTTP layer from the application logic.
 * The service is responsible for providing the student data.
 *
 * URL:
 * http://localhost:8080/exercise31/students
 */

package com.angel.springbootlearning.exercises.exercise31;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    
    private final List<String> students = new ArrayList<>(
        List.of("Ángel", "DoomGuy")
    );

    // Returns the student data without handling HTTP concerns
    public List<String> getStudents() { return students; }


}
