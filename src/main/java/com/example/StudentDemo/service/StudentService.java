package com.example.StudentDemo.service;


import com.example.StudentDemo.dto.StudentCreateDto;
import com.example.StudentDemo.dto.StudentUpdateDto;
import com.example.StudentDemo.mapper.StudentMapper;
import com.example.StudentDemo.model.Student;
import com.example.StudentDemo.repository.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    Map<Long, Student> allStudentList;

    public Student getStudent(UUID id) throws ChangeSetPersister.NotFoundException {

        return (Student) studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("Student not found with id: " + id)));
    }

   public List<Student>  getAllStudent() {

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
    public void deleteStudentById(UUID studentId) throws ChangeSetPersister.NotFoundException {
        Student student = (Student) studentRepository.findById(studentId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        studentRepository.delete(student);
    }

}
