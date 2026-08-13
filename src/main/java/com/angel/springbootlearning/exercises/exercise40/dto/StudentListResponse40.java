package com.angel.springbootlearning.exercises.exercise40.dto;

import java.util.List;

import com.angel.springbootlearning.exercises.exercise40.model.Student40;

public record StudentListResponse40(
    String message,
    List<Student40> students
) {}
