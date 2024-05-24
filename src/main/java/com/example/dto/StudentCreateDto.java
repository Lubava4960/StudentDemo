package com.example.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentCreateDto {
    private String surname;
    private int course;
    private LocalDate birthday;



}
