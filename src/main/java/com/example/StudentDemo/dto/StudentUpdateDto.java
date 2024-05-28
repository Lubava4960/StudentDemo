package com.example.StudentDemo.dto;

/**
 * DTO обновления данных студента
 */

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentUpdateDto {
        private String surname;
        private Integer course;
        private LocalDate birthday;

    }



