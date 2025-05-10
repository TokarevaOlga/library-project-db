package ru.itgirl.library_project.dto;

// DTO (Data Transfer Object) для регистрации нового пользователя
public class RegistrationRequest {


    private String username;
    private String password;



    // Геттеры и сеттеры
    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
