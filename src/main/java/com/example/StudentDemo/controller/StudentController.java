package com.example.StudentDemo.controller;


import com.example.StudentDemo.dto.StudentCreateDto;
import com.example.StudentDemo.dto.StudentUpdateDto;
import com.example.StudentDemo.model.Student;
import com.example.StudentDemo.repository.StudentRepository;
import com.example.StudentDemo.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Controller

@RequestMapping("/student")
public class StudentController {


    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Operation(
            summary = "получение списка студентов",
            tags= "cтуденты"
    )
    @GetMapping
    public  ResponseEntity<List<Student>> getAllStudents() {
        List<Student> allStudentList = studentService.getAllStudent();
      return (ResponseEntity<List<Student>>) studentService.getAllStudent();

    }

    @Operation(
            summary = "получение инфрмацию о студенте по id",
            tags= "cтуденты"
    )
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable ("id") UUID id) throws ChangeSetPersister.NotFoundException {

        Student student;
        student = studentService.getStudent(UUID.randomUUID());
     //   return ResponseEntity.ok(student);
        return studentService.getStudent(id);
   }

//    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
//       Student student = (Student) studentRepository.findById(UUID.fromString(String.valueOf(id))).orElse(null);
//        if (student == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(student);
//    }


    @Operation(
            summary = "можно создать данные нового студента ",
            tags= "cтуденты"
    )
    @PostMapping
    public ResponseEntity<StudentCreateDto> createStudent(@RequestBody StudentCreateDto newStudent) throws IOException {

        StudentCreateDto createStudent = studentService.createStudent(newStudent);
        return ResponseEntity.ok(createStudent);
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
            summary = "удаление данных студента по фамилии",
            tags= "cтуденты"
    )

    @DeleteMapping("/{surname}")
    public void deleteStudentBySurname(@PathVariable String surname) throws ChangeSetPersister.NotFoundException {
        studentService.deleteStudentBySurname(surname);
    }
    @Operation(
            summary = "удаление данных студента по id",
            tags= "cтуденты"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") UUID Id) {
        try {
            studentService.deleteStudentById(Id);
            return ResponseEntity.noContent().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }



}
