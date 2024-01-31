/**
 * This package includes repository for Competitions
 */
package com.codecool.cctask2024.model.repository;


import com.codecool.cctask2024.model.entity.CompetitionLadder;
import com.codecool.cctask2024.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * This interface contains CompetitionLadder Repository
 */
public interface CompetitionLadderRepository extends JpaRepository<CompetitionLadder, Long> {
    Optional<CompetitionLadder> findCompetitionLadderByCompetitionIdAndRoundAndPlayerOneOrPlayerTwo(Long competitionId, Integer round, Player playerOne, Player playerTwo);

    List<CompetitionLadder> findAllCompetitionLadderByCompetitionId(Long competitionId);
}
