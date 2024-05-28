package com.example.StudentDemo.model;
/**
 * Сущность студнет
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Students")
public class Student {

    @Id
    @GeneratedValue(generator = "uuid2")

    private UUID id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "course")
    private Integer course;
    @Column(name = "birthday")
    private LocalDate birthday;


}
