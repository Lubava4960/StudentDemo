package com.example.StudentDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResponseDto {
    private String message;
    private StudentCreateDto student;

}
