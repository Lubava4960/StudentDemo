package com.example.controller;


import com.example.dto.StudentCreateDto;
import com.example.dto.StudentUpdateDto;
import com.example.model.Student;
import com.example.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/student")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @Operation(
            summary = "получение списка студентов",
            tags= "cтуденты"
    )
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudent();
        return (ResponseEntity<List<Student>>) ResponseEntity.ok();
    }

    @Operation(
            summary = "получение студентф по id",
            tags= "cтуденты"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        try {
            Student studentDto = studentService.getStudentById(id);
            return ResponseEntity.ok(studentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Operation(
            summary = "можно создать данные нового студента ",
            tags= "cтуденты"
    )
    @PostMapping
    public StudentCreateDto createStudent(@RequestBody StudentCreateDto studentCreateDTO) {
        return studentService.createStudent(studentCreateDTO);
    }


    @Operation(
            summary = "обновление данных студента ",
            tags= "cтуденты"
    )
    @PutMapping("/{surname}")
    public Student updateStudent(@PathVariable String surname, @RequestBody StudentUpdateDto studentUpdateDto) throws ChangeSetPersister.NotFoundException {
        return studentService.updateStudent(surname, studentUpdateDto);
    }




    @Operation(
            summary = "удаление данных студента ",
            tags= "cтуденты"
    )

    @DeleteMapping("/{surname}")
    public void deleteStudentBySurname(@PathVariable String surname) throws ChangeSetPersister.NotFoundException {
        studentService.deleteStudentBySurname(surname);
    }


}
