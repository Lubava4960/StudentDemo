package com.example.StudentDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключить CSRF для тестирования (не рекомендуется для продакшн)
                .authorizeRequests()
                .antMatchers("/public/**").permitAll() // разрешить доступ к публичным страницам
                .antMatchers("http://localhost:8080/api/login").permitAll()
                .antMatchers("http://localhost:8080/api/users/register").permitAll()
                .antMatchers("/swagger-ui/").permitAll()
                .anyRequest().authenticated() // все остальные запросы требуют аутентификации
                .and()
                .formLogin()
                //.loginPage("/hello.html") //  свой путь к странице входа
                .permitAll() // разрешить доступ к странице входа
                .and()
                .logout()
                .permitAll(); // разрешить доступ ко выходу
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER") // пример пользователя
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN"); // пример администратора
    }

}
