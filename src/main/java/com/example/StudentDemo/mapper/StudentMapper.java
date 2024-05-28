package com.example.StudentDemo.mapper;

import com.example.StudentDemo.dto.StudentUpdateDto;
import com.example.StudentDemo.model.Student;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface StudentMapper {

         static Student toDTO(Student student) {
        Student studentDto = new Student();
        studentDto.setId(student.getId());
        studentDto.setSurname(student.getSurname());
        studentDto.setCourse(student.getCourse());
        studentDto.setBirthday(student.getBirthday());
        return studentDto;
    }


    static void updateStudentFromDto(Student student, StudentUpdateDto studentUpdateDto) {
        student.setSurname(studentUpdateDto.getSurname());
        student.setCourse(studentUpdateDto.getCourse());
        student.setBirthday(studentUpdateDto.getBirthday());
    }
}