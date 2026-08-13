package com.angel.springbootlearning.exercises.exercise40.dto;

import com.angel.springbootlearning.exercises.exercise40.model.Student40;

public record StudentResponse40 (
    String message,
    Student40 student
) {}
