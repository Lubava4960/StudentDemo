package com.example.StudentDemo.repository;

/**
 * Репозиторий для работы с бд таблицей студент
 */

import com.example.StudentDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAll();

    Optional<Object> findById(UUID id);


}