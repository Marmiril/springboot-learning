package com.angel.springbootlearning.exercises.exercise40.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.angel.springbootlearning.exercises.exercise40.dto.ErrorResponse40;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.angel.springbootlearning.exercises.exercise40")
public class GlobalExceptionHandler40 {

    @ExceptionHandler(InvalidStudentRequestException40.class)
    public ResponseEntity<ErrorResponse40> handleInvalidStudentRequest(
        InvalidStudentRequestException40  exception,
        HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse40 response = new ErrorResponse40(
            LocalDateTime.now(ZoneId.of("Europe/Madrid")),
            status.value(),
            status.getReasonPhrase(),
            exception.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity
            .status(status)
            .body(response);
    }   


    @ExceptionHandler(StudentNotFoundException40.class)
    public ResponseEntity<ErrorResponse40> handleStudentNotFound(
            StudentNotFoundException40 exception,
            HttpServletRequest request) {
        
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse40 response = new ErrorResponse40(
            LocalDateTime.now(ZoneId.of("Europe/Madrid")),
            status.value(),
            status.getReasonPhrase(),
            exception.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity
            .status(status)
            .body(response);        
        }
    
    
}
