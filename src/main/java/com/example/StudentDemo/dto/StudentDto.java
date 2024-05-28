package com.example.StudentDemo.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private UUID id;
    private String surname;
    private Integer course;
    private LocalDate birthday;


}
