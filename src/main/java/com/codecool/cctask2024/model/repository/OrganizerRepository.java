/**
 * This package includes repository for Players
 */
package com.codecool.cctask2024.model.repository;


import com.codecool.cctask2024.model.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface contains Organizer Repository
 */
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
}