package com.example.FindMates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.FindMates.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
