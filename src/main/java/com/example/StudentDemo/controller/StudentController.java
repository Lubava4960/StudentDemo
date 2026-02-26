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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            return ResponseEntity.ok(studentOpt.get());
        } else {
            log.warn("Студент не найден с ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(
            summary = "можно создать данные нового студента ",
            description = "введите данные студента",
            tags = "cтуденты"
    )

    @PostMapping
    public ResponseEntity<StudentCreateDto> createStudent(@RequestBody StudentCreateDto newStudent) throws IOException {
        StudentCreateDto createdStudent = studentService.createStudent(newStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @Operation(
            summary = "обновление данных студента по идентификатору ",
            description = "введите id студента и  измените данные",
            tags = "cтуденты"
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
