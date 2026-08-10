package com.angel.springbootlearning.exercises.exercise39;

import java.util.List;

public record StudentListResponse39 (
    String message,
    List<Student39> students
) {}
