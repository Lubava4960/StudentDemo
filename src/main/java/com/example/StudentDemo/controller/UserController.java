package com.example.StudentDemo.controller;

import com.example.StudentDemo.dto.UserDTO;
import com.example.StudentDemo.model.RegisterRequest;
import com.example.StudentDemo.model.Student;
import com.example.StudentDemo.model.User;
import com.example.StudentDemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Регистрация пользователя ",
            tags = "Пользователь"
    )

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
    }

    @Operation(
            summary = "Обновление данных пользователя ",
            tags = "Пользователь"
    )
    @PutMapping("/update/{username}")
    public ResponseEntity<Map<String, String>> updateLogin(
            @PathVariable String username,
            @RequestBody UserDTO userDTO) {
        return userService.updateLogin(username, userDTO);
    }

    @Operation(
            summary = "Получение списка всех зарегистрированных пользователей ",
            tags = "Пользователь"
    )
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Удаление пользователя по id",
            tags = "Пользователь"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Удаление пользователя по имени",
            tags = "Пользователь"
    )
    @DeleteMapping("/username")
    public ResponseEntity<Void> deleteUserByUsername(@RequestParam("username") String username) {
        userService.deleteAllUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

}

