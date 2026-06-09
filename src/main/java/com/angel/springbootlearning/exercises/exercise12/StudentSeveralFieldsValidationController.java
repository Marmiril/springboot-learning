/*
 * Exercise 12 - Validation of several fields
 *
 * Purpose:
 * This exercise extends manual validation by checking several fields
 * before creating a response: name, role, age and email.
 *
 * URL:
 * http://localhost:8080/exercise12/students
 */

package com.angel.springbootlearning.exercises.exercise12;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentSeveralFieldsValidationController {
    
    @PostMapping("/exercise12/students")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {

        if (isBlank(request.name())) { return badRequest("The student name is required"); }
        if (isBlank(request.role())) { return badRequest("The student role is required"); }
        if (isInvalidAge(request.age())) { return badRequest("The student age must be between 18 and 99!"); }
        if (isInvalidEmail(request.email())) { return badRequest("The student mail is not valid"); }

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
    private boolean isInvalidAge(Integer age) { return age == null  || age < 18 || age > 99; }
    private boolean isInvalidEmail(String email) { return isBlank(email) || !email.contains("@") || !email.contains("."); }

    // Reusable bad request response
    private ResponseEntity<ErrorResponse> badRequest(String message) {
        return new ResponseEntity<>(
            new ErrorResponse(message),
            HttpStatus.BAD_REQUEST
        );
    }
    public record CreateStudentRequest(String name, String role, Integer age, String email) {}
    public record StudentResponse(int id, String name, String role, Integer age, String email) {}
    public record ErrorResponse(String message) {}
}



/*
$body = @{
    name = "Angel"
    role = "backend"
    age = 38
    email = "angel@example.com"
} | ConvertTo-Json

Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise12/students" `
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
      -Uri "http://localhost:8080/exercise12/students" `
      -Method Post `
      -ContentType "application/json" `
      -Body $body
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
*/
