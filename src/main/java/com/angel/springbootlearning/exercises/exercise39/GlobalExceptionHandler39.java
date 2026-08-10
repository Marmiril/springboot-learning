package com.angel.springbootlearning.exercises.exercise39;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice(
    basePackages = "com.angel.springbootlearning.exercises.exercise39"
)
public class GlobalExceptionHandler39 {
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse39> handleResponseStatusException(
        ResponseStatusException exception
    ) {
        ErrorResponse39 errorResponse = new ErrorResponse39(
            exception.getStatusCode().value(),
            exception.getReason()
        );

        return ResponseEntity
            .status(exception.getStatusCode())
            .body(errorResponse);
    }

}
