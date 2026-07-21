/*
 * Exercise 26 - Complete student update
 *
 * Purpose:
 * This exercise replaces all the data of an existing student
 * using a PUT request and an id received as a path variable.
 *
 * URL:
 * http://localhost:8080/exercise26/students/{id}
 */

package com.angel.springbootlearning.exercises.exercise26;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/exercise26/students")
public class StudentFullUpdateController {

    private final List<Student> students = new ArrayList<>();

    // Initializes the in-memory list with sample students
    public StudentFullUpdateController() {
        students.add(new Student(1, "Angel", "Master in love and fullstack"));
        students.add(new Student(2, "Kratos", "God of War"));
    }

    // Handles PUT requests sent to /exercise26/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Student> upsateStudents(
        @PathVariable int id,
        @RequestBody Student receivedStudent) {

            // Iterates through the list using each student`s position
            for (int index = 0; index < students.size(); index++) {
                
                // Gets the student stored at the current position
                Student currentStudent = students.get(index);

                // Checks whether the stored student`s id matches the requested id
                if (currentStudent.id() == id) {

                    // Creates a new student using the URL id and the received data 
                    Student updatedStudent = new Student(
                        id, receivedStudent.name(),
                        receivedStudent.role()
                    );

                    // Replaces the existing student with the updated student
                    students.set(index, updatedStudent);

                    // Returns the updated student withd HTTPS 200 OK
                    return ResponseEntity.ok(updatedStudent);
                }
            }

            // Returns HTTP 404 NOT FOUND when the student does not exist
            return ResponseEntity.notFound().build();
        }

    public record Student (int id, String name, String role) {}    
}

/*
$body = @{
    id   = 1
    name = "Angel Plata"
    role = "Java developer"
} | ConvertTo-Json

Invoke-RestMethod `
    -Uri "http://localhost:8080/exercise26/students/1" `
    -Method Put `
    -ContentType "application/json" `
    -Body $body
*/

/*
-- 404 --
$body = @{
    id   = 99
    name = "Unknown"
    role = "Developer"
} | ConvertTo-Json

try {
    Invoke-WebRequest `
        -Uri "http://localhost:8080/exercise26/students/99" `
        -Method Put `
        -ContentType "application/json" `
        -Body $body `
        -UseBasicParsing
}
catch {
    $_.Exception.Response.StatusCode.value__
}
*/
