package com.example.StudentDemo.service;

import com.example.StudentDemo.dto.UserDTO;
import com.example.StudentDemo.model.Student;
import com.example.StudentDemo.model.User;
import com.example.StudentDemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Map<String, String>> registerUser(String username, String password) {
        Map<String, String> response = new HashMap<>();

        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            response.put("message", "Пользователь с таким именем уже существует");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);

            response.put("message", "Регистрация успешна");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Ошибка регистрации: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<Map<String, String>> updateLogin(String username, UserDTO userDTO) {
        Map<String, String> response = new HashMap<>();
        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isPresent()) {
            User user = existingUser.get();

            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

            if (userDTO.getUsername() != null) {
                user.setUsername((userDTO.getUsername()));
            }
            user.setCreatedAt(userDTO.getCreatedAt());

            userRepository.save(user);
            response.put("message", "Данные успешно обновлены");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Пользователь не найден");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUser(UUID Id) {
        userRepository.deleteById(Id);
    }

    public void deleteAllUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new RuntimeException("Пользователь с таким именем не найден");
        }
    }
}




