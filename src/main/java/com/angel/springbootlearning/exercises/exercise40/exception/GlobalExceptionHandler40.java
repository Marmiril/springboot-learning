package com.angel.springbootlearning.exercises.exercise40.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.angel.springbootlearning.exercises.exercise40.dto.ErrorResponse40;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.angel.springbootlearning.exercises.exercise40")
public class GlobalExceptionHandler40 {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse40> handleResponseStatusException(
        ResponseStatusException exception,
        HttpServletRequest request) {
        int status = exception.getStatusCode().value();

        ErrorResponse40 response = new ErrorResponse40(
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
