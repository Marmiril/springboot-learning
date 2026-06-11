/*
 * Exercise 13 - Structured error responses
 *
 * Purpose:
 * This exercise improves error handling by returning a more structured
 * error response. Instead of returning only a message, the API now returns
 * the HTTP status code, the HTTP error name and a custom validation message.
 *
 * URL:
 * http://localhost:8080/exercise13/students
 */


package com.angel.springbootlearning.exercises.exercise13;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentStructuredErrorController {
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    

    @PostMapping("/exercise13/students")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {

        if (isBlank(request.name())) { return badRequest("Student name is required"); }
        if (isBlank(request.role())) { return badRequest("Student role is mandatory"); }
        if (isInvalidAge(request.age())) { return badRequest("Student age must be between 18 - 99"); }
        if (isInvalidEmail(request.email())) { return badRequest("Email format is not valid"); }

        StudentResponse response = new StudentResponse(
            1,
            request.name(),
            request.role(),
            request.age(),
            request.email()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }
    private boolean isInvalidAge(Integer age) { return age == null || age < 18 || age > 99; }
    private boolean isInvalidEmail(String email) { return isBlank(email) || !email.matches(EMAIL_REGEX); }

    // Builds a reusable structured request response
    private ResponseEntity<ErrorResponse> badRequest(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }
 
    // Defines the data expected from the request body
    public record CreateStudentRequest(String name, String role, Integer age, String email) {}

    // Defines the successful response model
    public record StudentResponse(int id, String name, String role, Integer age, String email) {}

    // Defines the structured error response model
    public record ErrorResponse(int status, String error, String message) {}
}
 /*
$body = @{
    name = "Angel"
    role = "backend"
    age = 38
    email = "angel@example.com"
} | ConvertTo-Json

Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise13/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body $body
*/


// ERROR RESPONSE
/*
$body = @{
    name = "Angel"
    role = "backend"
    age = 15
    email = "angel@example.com"
} | ConvertTo-Json

try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise13/students" `
      -Method Post `
      -ContentType "application/json" `
      -Body $body
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
*/