/*
 * Exercise 39 Bis - Advanced Layered CRUD
 *
 * Purpose:
 * Converts controlled exceptions into uniform JSON responses.
 *
 * URLs:
 * http://localhost:8080/exercise39bis/students
 * http://localhost:8080/exercise39bis/students/{id}
 */

package com.angel.springbootlearning.exercises.ex39bis;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.angel.springbootlearning.exercises.ex39bis.StudentClassResponse39Bis.ErrorResponse39Bis;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.angel.springbootlearning.exercises.ex39bis")
public class GlobalExceptionHandler39Bis {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse39Bis> handleResponseStatusException(
            ResponseStatusException exception,
            HttpServletRequest request) {
        int status = exception.getStatusCode().value();

        ErrorResponse39Bis response = new ErrorResponse39Bis(
                LocalDateTime.now(ZoneId.of("Europe/Madrid")),
                status,
                HttpStatus.valueOf(status).getReasonPhrase(),
                exception.getReason(),
                request.getRequestURI()
        );
        
        return ResponseEntity
            .status(exception.getStatusCode())
            .body(response);

    }
}
