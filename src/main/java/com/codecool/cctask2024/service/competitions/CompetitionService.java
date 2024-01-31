package com.codecool.cctask2024.service.competitions;


import com.codecool.cctask2024.model.entity.Competition;

import java.util.List;

public interface CompetitionService {

    /**
     * This method finding the all Competition Objects from database
     *
     * @return Returns all Competitions
     */
    List<Competition> getAllCompetitions();

    Competition getCompetitionById(Long competitionId);

    /**
     * This method saving the Competition model to database
     *
     * @param competition Model Competition description a competition
     */
    void createCompetition(Competition competition);

    void addPlayer(Long competitionId, Long playerId);
}
