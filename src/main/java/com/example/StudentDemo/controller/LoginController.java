package com.example.StudentDemo.controller;

import com.example.StudentDemo.model.LoginRequest;
import com.example.StudentDemo.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class LoginController {
    private final LoginService loginService;
    @Operation(
            summary = "Аутентификация пользователя",
            description = "Вход в приложение",
            tags = "аутентификация"
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        boolean isAuthenticated = loginService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Успешный вход");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверное имя пользователя или пароль");
        }
    }
}
