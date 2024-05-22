package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name= "Students")
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
    @Column(name = "surname")
private String surname;
    @Column(name = "course")
private Integer course;
    @Column(name = "birthday")
private LocalDate birthday;

}
