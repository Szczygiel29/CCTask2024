/**
 * This package includes repository for Competitions
 */
package com.codecool.cctask2024.model.repository;


import com.codecool.cctask2024.model.entity.Club;
import com.codecool.cctask2024.model.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface contains Competition Repository
 */
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByCoach(Coach coach);
}
