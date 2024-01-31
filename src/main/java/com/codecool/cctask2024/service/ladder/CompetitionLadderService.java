package com.codecool.cctask2024.service.ladder;


import com.codecool.cctask2024.model.entity.CompetitionLadder;

import java.util.List;

public interface CompetitionLadderService {

    List<CompetitionLadder> startCompetition(Long competitionId);

    List<CompetitionLadder> startNextRound(Long competitionId);

    List<CompetitionLadder> findLaddersForCompetition(Long competitionId);

    CompetitionLadder setWinner(Long competitionId, Integer roundNumber, Long winnerId);
}
