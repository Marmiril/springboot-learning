package com.angel.springbootlearning.exercises.exercise40Bis.dto;

import com.angel.springbootlearning.exercises.exercise40Bis.model.Student40Bis;

public record StudentResponse40Bis (
    String message,
    Student40Bis student
) {}
