package com.angel.springbootlearning.exercises.exercise38;

import java.util.List;

public record StudentListResponse38 (
    String message,
    List<Student38> students
){}
