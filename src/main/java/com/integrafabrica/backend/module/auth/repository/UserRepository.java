package com.integrafabrica.backend.module.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.integrafabrica.backend.module.auth.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT u FROM User u
            JOIN FETCH u.role
            WHERE u.username = :username OR u.email = :email
            """)
    Optional<User> findByUsernameOrEmail(
            @Param("username") String username,
            @Param("email") String email);

    @Query("""
            SELECT u FROM User u
            JOIN FETCH u.role
            WHERE u.username = :username
            """)
    Optional<User> findByUsername(@Param("username") String username);

    @Query("""
            SELECT u FROM User u
            JOIN FETCH u.role
            WHERE u.id = :id
            """)
    Optional<User> findByIdWithRole(@Param("id") Long id);
}
