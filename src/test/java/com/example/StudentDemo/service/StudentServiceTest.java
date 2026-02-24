package com.example.StudentDemo.service;

import com.example.StudentDemo.model.Student;
import com.example.StudentDemo.repository.StudentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private UUID studentId;
    private Student student;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


        studentId = UUID.randomUUID();
        student = new Student();
        student.setId(studentId);
        student.setSurname("Doe");
        student.setCourse(1);
        student.setBirthday(LocalDate.of(2000, 1, 1));
    }

    @Test
    public void testGetStudent() {
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            studentService.getStudent(studentId);
        });

        assertEquals("Student not found with id: " + studentId, exception.getMessage()); // Проверка сообщения об ошибке

        Student result = studentService.getStudent(studentId);

        assertNotNull(result);
        assertEquals("Doe", result.getSurname());



    }
}