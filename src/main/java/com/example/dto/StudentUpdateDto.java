package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Data
@Getter
@Setter
@AllArgsConstructor
public class StudentUpdateDto {


        private UUID id;
        private String surname;
        private Integer course;
        private LocalDate birthday;
    }



