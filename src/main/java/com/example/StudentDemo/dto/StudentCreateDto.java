package com.example.StudentDemo.dto;
/**
 * DTO  создания данных студента
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateDto {
    private UUID id;
    private String surname;
    private int course;
    @NotNull(message = "Дата рождения не должна быть пустой")
    private LocalDate birthday;


}
