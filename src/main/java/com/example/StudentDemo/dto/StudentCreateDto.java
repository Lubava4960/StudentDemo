package com.example.StudentDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateDto {
    private UUID id;
    private String surname;
    private int course;
    private LocalDate birthday;



}
