package ru.itgirl.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.library_project.model.AppUser;

import java.util.Optional;





public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // Метод для поиска пользователя по username
    Optional<AppUser> findByUsername(String username);
}
