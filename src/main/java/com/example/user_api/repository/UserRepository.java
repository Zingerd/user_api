package com.example.user_api.repository;

import com.example.user_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByBirthDateBetween(LocalDate from, LocalDate to);
}
