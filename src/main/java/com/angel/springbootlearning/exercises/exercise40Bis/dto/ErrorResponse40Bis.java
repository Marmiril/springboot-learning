package com.angel.springbootlearning.exercises.exercise40Bis.dto;

import java.time.LocalDateTime;

public record ErrorResponse40Bis (
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    String path
)  {}
