package com.example.StudentDemo.repository;

import com.example.StudentDemo.dto.StudentCreateDto;
import com.example.StudentDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface StudentRepository extends JpaRepository<Student, Integer> {
    static void save(StudentCreateDto student) {
    }
    List<Student> findAll();
    void deleteById(UUID id);

    Optional<Student> findBySurname(String surname);


    Optional<Object> findById(UUID id);

}