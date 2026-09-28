package com.angel.springbootlearning.exercises.exercise40Bis.dto;

import java.util.List;

import com.angel.springbootlearning.exercises.exercise40Bis.model.Student40Bis;

public record StudentListResponse40Bis (
    String message,
    List<Student40Bis> students
) {}
