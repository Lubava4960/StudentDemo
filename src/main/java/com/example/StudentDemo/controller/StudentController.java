package com.example.StudentDemo.controller;
/**
 * контроллер для получения и обработки входящих запросов
 */

import com.example.StudentDemo.dto.StudentCreateDto;
import com.example.StudentDemo.dto.StudentDto;
import com.example.StudentDemo.dto.StudentUpdateDto;
import com.example.StudentDemo.model.Student;
import com.example.StudentDemo.repository.StudentRepository;
import com.example.StudentDemo.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Data
@AllArgsConstructor
@RestController

@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Operation(
            summary = "получение списка студентов",
            tags = "cтуденты"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Списки выведены"
    )
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }

    @Operation(
            summary = "получение информацию о студенте по id",
            description = "введите id студента",
            tags = "cтуденты"
    )


    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Студент найден"),
            @ApiResponse(responseCode = "404", description = "Студент не найден")
    })
    public Student getStudentById(@PathVariable UUID id) {
        // Поиск студента по ID
        Student student = (Student) studentRepository.findById(id).orElse(null);
        if (student == null) {
            log.warn("Студент не найден с ID: " + id);
            return null;
        }

        return student;
    }

    @Operation(
            summary = "можно создать данные нового студента ",
            description = "введите данные студента",
            tags = "cтуденты"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Данные созданы"
    )
    @PostMapping
    public ResponseEntity<StudentCreateDto> createStudent(@RequestBody StudentCreateDto newStudent) throws IOException {
        StudentCreateDto studentCreateDto = studentService.createStudent(newStudent);
        return ResponseEntity.ok(studentCreateDto);
    }

    @Operation(
            summary = "обновление данных студента по идентификатору ",
            description = "введите id студента и  измените данные",
            tags = "cтуденты"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Данные обновлены"
    )

    @PutMapping("/update/{id}")
    public StudentDto updateStudentById(@PathVariable UUID id, @RequestBody StudentUpdateDto studentUpdateDto) throws ChangeSetPersister.NotFoundException {
        return studentService.updateStudent((id), studentUpdateDto);
    }

    @Operation(
            summary = "удаление данных студента по id",
            description = "введите id студента",
            tags = "cтуденты"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Данные удалены"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Данные не удалены"
    )

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") UUID Id) {
        try {
            studentService.deleteStudentById(Id);
            return ResponseEntity.noContent().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
