/*
 * Exercise 38 - Complete layered flow
 *
 * Purpose:
 * Handles exceptions from exercise 38 and returns
 * a consistent JSON error response.
 *
 * URL:
 * http://localhost:8080/exercise38/students
 */

package com.angel.springbootlearning.exercises.exercise38;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice(
    basePackages = "com.angel.springbootlearning.exercises.exercise38"
)

public class GlobalExceptionHandler38 {

    // Converts ResponseStatusException into a custom JSON response
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse38> handleResponseStatusException(
        ResponseStatusException exception
    ) {
        ErrorResponse38 errorResponse = new ErrorResponse38(
            exception.getStatusCode().value(),
            exception.getReason()
        );

        return ResponseEntity
            .status(exception.getStatusCode())
            .body(errorResponse);
    }

}

