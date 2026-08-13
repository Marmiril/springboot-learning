package com.angel.springbootlearning.exercises.exercise40.dto;

import java.time.LocalDateTime;

public record ErrorResponse40 (
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    String path
) {}
