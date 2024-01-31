/**
 * This package includes repository for Players
 */
package com.codecool.cctask2024.model.repository;


import com.codecool.cctask2024.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface contains Player Repository
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
