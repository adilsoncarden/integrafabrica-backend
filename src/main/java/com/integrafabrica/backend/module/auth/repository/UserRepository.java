package com.integrafabrica.backend.module.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrafabrica.backend.module.auth.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
}