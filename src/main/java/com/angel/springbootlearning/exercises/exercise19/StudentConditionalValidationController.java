/*
 * Exercise 19 - Conditional validation
 *
 * Purpose:
 * This exercise introduces conditional validation. Some fields are required
 * only when another field has a specific value.
 *
 * URL:
 * http://localhost:8080/exercise19/students
 */

package com.angel.springbootlearning.exercises.exercise19;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentConditionalValidationController {

    @PostMapping("/exercise19/students")
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {

        if (isBlank(request.name())) {
            return badRequest("Student name is required");
        }

        if (isBlank(request.role())) {
            return badRequest("Student role is required");
        }

        if (!isAllowedRole(request.role())) {
            return unprocessableContent("Student role must be backend, frontend or fullstack");
        }

        if (requiresPortfolio(request.role()) && isBlank(request.portfolioUrl())) {
            return unprocessableContent("Portfolio URL is required for frontend and fullstack students");
        }

        StudentResponse response = new StudentResponse(
                1,
                request.name(),
                request.role(),
                request.portfolioUrl());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    // Defines the allowed student roles
    private List<String> getAllowedRoles() {
        return List.of("backend", "frontend", "fullstack");
    }

    // Checks if the received role is allowed
    private boolean isAllowedRole(String role) {
        return getAllowedRoles().stream()
                .anyMatch(allowedRole -> allowedRole.equalsIgnoreCase(role));
    }

    // Checks if the selected role requires a portfolio URL
    private boolean requiresPortfolio(String role) {
        return role.equalsIgnoreCase("frontend") || role.equalsIgnoreCase("fullstack");
    }

    // Builds a reusable structured bad request response
    private ResponseEntity<ErrorResponse> badRequest(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message);

        return new ResponseEntity<>(errorResponse, status);
    }

    // Builds a reusable structured unprocessable content response
    private ResponseEntity<ErrorResponse> unprocessableContent(String message) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;

        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message);

        return new ResponseEntity<>(errorResponse, status);
    }

    // Defines the data expected from the reques body
    public record CreateStudentRequest(String name, String role, String portfolioUrl) {
    }

    // Defines the succesful response model
    public record StudentResponse(int id, String name, String role, String portfolioUrl) {
    }

    // Defines the structured error response model
    public record ErrorResponse(int status, String error, String message) {
    }
}

/**
 * -- FAIL --
$body = @{
    name = "Angel"
    role = "frontend"
    portfolioUrl = ""
} | ConvertTo-Json

try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise19/students" `
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
$body = @{
    name = "Angel"
    role = "backend"
    portfolioUrl = ""
} | ConvertTo-Json

Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise19/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body $body
*/