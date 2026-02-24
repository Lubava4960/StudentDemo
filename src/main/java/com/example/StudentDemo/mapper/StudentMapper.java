package com.example.StudentDemo.mapper;

import com.example.StudentDemo.dto.StudentDto;
import com.example.StudentDemo.dto.StudentUpdateDto;
import com.example.StudentDemo.model.Student;

public class StudentMapper implements Mapper<Student, StudentUpdateDto>{
    @Override
    public Student map(StudentUpdateDto surSource) {
     Student student=new Student() ;
     student.setSurname(surSource.getSurname());
     student.setCourse(surSource.getCourse());
     student.setBirthday(surSource.getBirthday());

        return student;
    }
    public static void updateStudentFromDto(Student student, StudentUpdateDto studentUpdateDto) {
        if (studentUpdateDto.getSurname() != null) {
            student.setSurname(studentUpdateDto.getSurname());
        }
        if (studentUpdateDto.getCourse() != null) {
            student.setCourse(studentUpdateDto.getCourse());
        }
        if(studentUpdateDto.getBirthday()!=null){
            student.setBirthday(studentUpdateDto.getBirthday());
        }
    }
    public StudentDto toDTO(Student student) {
        StudentDto dto = new StudentDto();
        dto.setSurname(student.getSurname());
        dto.setCourse(student.getCourse());
        dto.setBirthday(student.getBirthday());
        return dto;
    }

}
