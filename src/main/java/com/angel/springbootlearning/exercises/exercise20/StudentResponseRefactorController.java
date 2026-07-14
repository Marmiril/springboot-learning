/*
 * Exercise 20 - Refactor responses
 *
 * Purpose:
 * This exercise refactors response creation by using private helper methods
 * for both successful and error responses.
 *
 * URL:
 * http://localhost:8080/exercise20/students
 */

package com.angel.springbootlearning.exercises.exercise20;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentResponseRefactorController {
    
    @PostMapping("/exercise20/students")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {

        if (isBlank(request.name())) { return badRequest("Student name is required"); }
        if (isBlank(request.role())) { return badRequest("Student role is required"); }
        if (!isAllowedRole(request.role())) { return unprocessableContent ("Studentrole must be backend, frontend or fullstack"); }

        StudentResponse response = new StudentResponse(
            1,
            request.name(),
            request.role()
        );

        return created(response);
    }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }

    private List<String> getAllowedRoles() { return List.of("backend", "frontend", "fullstack"); }

    private boolean isAllowedRole(String role) { return getAllowedRoles().stream().anyMatch(allowedRole -> allowedRole.equalsIgnoreCase(role)); }

    private ResponseEntity<StudentResponse> created(StudentResponse response) { return new ResponseEntity<>(response, HttpStatus.CREATED); }

    private ResponseEntity<ErrorResponse> badRequest (String message) { return error(HttpStatus.BAD_REQUEST, message); }

    private ResponseEntity<ErrorResponse> unprocessableContent(String message) { return error(HttpStatus.UNPROCESSABLE_CONTENT, message); }

    private ResponseEntity<ErrorResponse> error (HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    public record CreateStudentRequest(String name, String role) {}
    public record StudentResponse(int id, String name, String role) {}
    public record ErrorResponse(int status, String error, String message) {}

}


/**
 * -- CREATED -- 201
 * $body = @{
    name = "Angel"
    role = "backend"
} | ConvertTo-Json

Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise20/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body $body 
 */

// ///////////////////////////////////////////////////////////////////////////////////////
 
/*
    -- BAD REQUEST 404
$body = @{
    name = ""
    role = "backend"
} | ConvertTo-Json

try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise20/students" `
      -Method Post `
      -ContentType "application/json" `
      -Body $body `
      -UseBasicParsing
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
 */

/*
    -- UNPROCESSABLE CONTENT -- 422
$body = @{
    name = "Angel"
    role = "designer"
} | ConvertTo-Json

try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise20/students" `
      -Method Post `
      -ContentType "application/json" `
      -Body $body `
      -UseBasicParsing
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}

*/