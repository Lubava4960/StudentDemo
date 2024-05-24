package com.example.mapper;

import com.example.dto.StudentCreateDto;
import com.example.dto.StudentDto;
import com.example.dto.StudentUpdateDto;
import com.example.model.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface StudentMapper {
    static StudentCreateDto toCreateDto(Student student) {
        StudentCreateDto studentCreateDto = new StudentCreateDto();
        studentCreateDto.setSurname(student.getSurname());
        studentCreateDto.setCourse(student.getCourse());
        studentCreateDto.setBirthday(student.getBirthday());
        return studentCreateDto;
    }

    static Student toEntity(StudentCreateDto studentCreateDTO) {
        Student student = new Student();
        student.setSurname(studentCreateDTO.getSurname());
        student.setCourse(studentCreateDTO.getCourse());
        student.setBirthday(studentCreateDTO.getBirthday());
        return student;
    }

    static Student toDTO(Student student) {
        Student studentDto = new Student();
        studentDto.setId(student.getId());
        studentDto.setSurname(student.getSurname());
        studentDto.setCourse(student.getCourse());
        studentDto.setBirthday(student.getBirthday());
        return studentDto;
    }

    static Student toEntity(StudentDto studentDTO) {
        Student student = new Student();
        student.setSurname(studentDTO.getSurname());
        student.setCourse(studentDTO.getCourse());
        student.setBirthday(studentDTO.getBirthday());
        return student;
    }


    static void studentUpdateDto(Student student, StudentUpdateDto studentUpdateDto) {

        student = new Student();
        student.setSurname(studentUpdateDto.getSurname());
        student.setCourse(studentUpdateDto.getCourse());
        student.setBirthday(studentUpdateDto.getBirthday());



    }
}