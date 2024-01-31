package com.codecool.cctask2024.controller.resources;


import com.codecool.cctask2024.model.contract.SetWinnerRequest;
import com.codecool.cctask2024.model.entity.CompetitionLadder;

import java.util.List;

public interface CompetitionLadderResource {

    String START_COMPETITION = "/start/{competitionId}";
    String START_NEXT_ROUND = "/{competitionId}";
    String FIND_LADDERS_FOR_COMPETITION = "ladders/{competitionId}";
    String SET_WINNER = "/set-winner";

    List<CompetitionLadder> startCompetition(Long competitionId);

    List<CompetitionLadder> startNextRound(Long competitionId);

    List<CompetitionLadder> findLaddersForCompetition(Long competitionId);

    CompetitionLadder setWinner(SetWinnerRequest request);
}
