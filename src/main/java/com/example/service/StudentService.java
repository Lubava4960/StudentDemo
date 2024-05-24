package com.example.service;


import com.example.dto.StudentCreateDto;

import com.example.dto.StudentUpdateDto;
import com.example.mapper.StudentMapper;
import com.example.model.Student;
import com.example.repository.StudentRepository;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.UUID;


@Service
public class StudentService {
    private final StudentRepository studentRepository = null;

    public Student getStudentById(UUID id) throws ChangeSetPersister.NotFoundException {
        Student student = (Student) studentRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return StudentMapper.toDTO(student);


    }
   public List<Student> getAllStudent() {
    return studentRepository.findAll();
}




    public StudentCreateDto createStudent(StudentCreateDto studentCreateDTO) {
        if (studentCreateDTO.getSurname() == null || studentCreateDTO.getSurname().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty");
        }

        Student student = new Student();
        student.setSurname(studentCreateDTO.getSurname());
        student.setCourse(studentCreateDTO.getCourse());
        student.setBirthday(studentCreateDTO.getBirthday());

        Student savedStudent = studentRepository.save(student);
        return (studentCreateDTO);
    }



    public Student updateStudent(String surname, StudentUpdateDto studentUpdateDto) throws ChangeSetPersister.NotFoundException {
        Student student = studentRepository.findBySurname(surname)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        StudentMapper.studentUpdateDto(student, studentUpdateDto);
        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.toDTO(updatedStudent);
    }

    public void deleteStudentBySurname(String surname) throws ChangeSetPersister.NotFoundException {
        Student student = studentRepository.findBySurname(surname)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        studentRepository.delete(student);
    }



}
