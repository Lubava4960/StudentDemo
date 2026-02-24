package com.example.StudentDemo.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/api/users")
                .allowedOrigins("http://localhost:3000") // Укажите адрес вашего фронтенда
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}

