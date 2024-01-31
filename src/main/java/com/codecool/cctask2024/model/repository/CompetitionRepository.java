/**
 * This package includes repository for Competitions
 */
package com.codecool.cctask2024.model.repository;


import com.codecool.cctask2024.model.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface contains Competition Repository
 */
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
