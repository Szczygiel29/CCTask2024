/**
 * This package includes repository for Competitions
 */
package com.codecool.cctask2024.model.repository;


import com.codecool.cctask2024.model.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface contains Competition Repository
 */
public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findByFirstNameAndLastName(String firstName, String lastName);
}
