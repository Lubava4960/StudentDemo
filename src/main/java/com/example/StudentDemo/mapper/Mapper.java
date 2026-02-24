package com.example.StudentDemo.mapper;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


//@Mapper(componentModel = "spring")

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Mapper<D, S> {
    D map(S surSource);

    default Set<D> toSet(List<S> surSourceList) {
        return surSourceList.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

    default List<D> toList(List<S> surSourceList){
        return surSourceList.stream()
                .map(this::map)
                .toList();
    }
}


//      static Student toDTO(Student student) {
//        Student studentDto = new Student();
//        studentDto.setId(student.getId());
//        studentDto.setSurname(student.getSurname());
//        studentDto.setCourse(student.getCourse());
//        studentDto.setBirthday(student.getBirthday());
//        return studentDto;
//
//      }
//
//
//    static void updateStudentFromDto(Student student, StudentUpdateDto studentUpdateDto) {
//        student.setSurname(studentUpdateDto.getSurname());
//        student.setCourse(studentUpdateDto.getCourse());
//        student.setBirthday(studentUpdateDto.getBirthday());
//    }
