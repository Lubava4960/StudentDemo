package com.example.StudentDemo.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name= "app_user")
public class User {

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        private UUID id;

        @Column(unique = true, nullable = false)
        private String username;

        @Column(nullable = false)
        private String password;

        @Column(name = "student_id")
        private UUID studentId;

        @CreationTimestamp
        @Column(name = "created_at")
        private LocalDateTime createdAt;


}
