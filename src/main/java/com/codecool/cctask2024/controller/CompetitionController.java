package com.codecool.cctask2024.controller;


import com.codecool.cctask2024.controller.resources.CompetitionResource;
import com.codecool.cctask2024.model.contract.AddPlayerRequest;
import com.codecool.cctask2024.model.entity.Competition;
import com.codecool.cctask2024.service.competitions.CompetitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.codecool.cctask2024.configuration.ApiConstraints.COMPETITION;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(COMPETITION)
public class CompetitionController implements CompetitionResource {

    private final CompetitionService competitionService;

    @GetMapping(value = FIND_ALL_COMPETITIONS)
    public List<Competition> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @GetMapping(value = FIND_COMPETITION_BY_ID)
    public Competition getCompetitionById(@PathVariable Long competitionId) {
        return competitionService.getCompetitionById(competitionId);
    }

    @PostMapping(value = ADD_PLAYER)
    public void addPlayer(@RequestBody AddPlayerRequest request) {
        competitionService.addPlayer(request.getCompetitionId(), request.getPlayerId());
    }

    @PostMapping(value = SAVE_COMPETITION)
    public void saveCompetition(@RequestBody Competition competition) {
        competitionService.createCompetition(competition);
    }

}
