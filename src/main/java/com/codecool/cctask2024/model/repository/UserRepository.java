package com.codecool.cctask2024.model.repository;


import com.codecool.cctask2024.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface contains User Repository
 */
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserNameOrUserEmail(String name, String email);

    Optional<User> findByUserEmail(String userEmail);
}
