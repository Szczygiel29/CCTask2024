package com.codecool.cctask2024.controller;


import com.codecool.cctask2024.controller.resources.CompetitionLadderResource;
import com.codecool.cctask2024.model.contract.SetWinnerRequest;
import com.codecool.cctask2024.model.entity.CompetitionLadder;
import com.codecool.cctask2024.service.ladder.CompetitionLadderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.codecool.cctask2024.configuration.ApiConstraints.LADDERS;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(LADDERS)
public class CompetitionLaddersController implements CompetitionLadderResource {

    CompetitionLadderService competitionLadderService;

    @Override
    @GetMapping(START_COMPETITION)
    public List<CompetitionLadder> startCompetition(@PathVariable Long competitionId) {
        return competitionLadderService.startCompetition(competitionId);
    }

    @Override
    @GetMapping(START_NEXT_ROUND)
    public List<CompetitionLadder> startNextRound(@PathVariable Long competitionId) {
        return competitionLadderService.startNextRound(competitionId);
    }

    @Override
    @GetMapping(FIND_LADDERS_FOR_COMPETITION)
    public List<CompetitionLadder> findLaddersForCompetition(@PathVariable Long competitionId) {
        return competitionLadderService.findLaddersForCompetition(competitionId);
    }

    @Override
    @PostMapping(SET_WINNER)
    public CompetitionLadder setWinner(@RequestBody SetWinnerRequest request) {
        return competitionLadderService.setWinner(request.getCompetitionId(), request.getRoundNumber(), request.getWinnerId());
    }
}
