package ru.itgirl.library_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.itgirl.library_project.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity // Включает возможности Spring Security для веб-приложения
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Настройка цепочки фильтров безопасности
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Определение правил доступа:
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/book").hasRole("USER")
                        .requestMatchers("/book/v2").hasRole("ADMIN")
                        .requestMatchers("/books").hasRole("ADMIN")
                        .requestMatchers("/api/register", "login", "/css/", "/js/", "/img/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // Включение HTTP Basic Authentication
                // Отключаем CSRF для простоты взаимодействия с REST API
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // Бин для шифрования паролей с использованием BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Благодаря наличию бина CustomUserDetailsService Spring Security автоматически использует его для аутентификации.
}