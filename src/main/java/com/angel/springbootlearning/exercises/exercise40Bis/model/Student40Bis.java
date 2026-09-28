package com.angel.springbootlearning.exercises.exercise40Bis.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record Student40Bis (
    int id,
    String name,
    String role,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime registrationDate    
) {}
