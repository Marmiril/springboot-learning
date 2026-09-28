package com.angel.springbootlearning.exercises.exercise40Bis.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.angel.springbootlearning.exercises.exercise40Bis")
public class GlobalExceptionHandler40Bis {

    @ExceptionHandler(InvalidStudentRequestException40Bis.class)
    public ResponseEntity<ErrorResponse> handleInvalidStudentRequest(
            InvalidStudentRequestException40Bis exception,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse40Bis responde = new ErrorResponse40Bis(
                LocalDateTime.now(ZoneId.of("Europe/Madrid")),
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity
                .status(status)
                .body(response);
    }

}
