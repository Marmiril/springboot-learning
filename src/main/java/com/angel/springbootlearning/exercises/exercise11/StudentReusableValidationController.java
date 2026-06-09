/*
 * Exercise 11 - Reusable validation with private methods
 *
 * Purpose:
 * This exercise improves the previous manual validation by extracting
 * repeated validation logic into private methods. This makes the controller
 * cleaner and easier to maintain.
 *
 * URL:
 * http://localhost:8080/exercise11/students
 */

package com.angel.springbootlearning.exercises.exercise11;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentReusableValidationController {
    
    @PostMapping("/exercise11/students")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {
        
        if (isBlank(request.name())) { return badRequest("The student name is required!"); }
        if (isBlank(request.role())) { return badRequest("The student role is required!"); }

        StudentResponse response = new StudentResponse(
            1,
            request.name(),
            request.role()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    // Builds a reusable bad request response
    private ResponseEntity<ErrorResponse> badRequest(String message) {
        return new ResponseEntity<>(
            new ErrorResponse(message),
            HttpStatus.BAD_REQUEST
        );
    }
    // Defines de data expected from the request body
    public record CreateStudentRequest(String name, String role) {}
    // Defines the successfull response model
    public record StudentResponse(int id, String name, String role) {}
    // Defines the error response model
    public record ErrorResponse(String message) {}
}

/*
Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise11/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"name":"Angel","role":"backend"}'
   */


  // BAD REQUEST
  /*
  $body = @{
    name = ""
    role = "backend"
} | ConvertTo-Json

try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise11/students" `
      -Method Post `
      -ContentType "application/json" `
      -Body $body
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
    */