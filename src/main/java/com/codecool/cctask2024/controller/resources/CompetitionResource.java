package com.codecool.cctask2024.controller.resources;


import com.codecool.cctask2024.model.contract.AddPlayerRequest;
import com.codecool.cctask2024.model.entity.Competition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface CompetitionResource {

    String FIND_ALL_COMPETITIONS = "/all";
    String FIND_COMPETITION_BY_ID = "/{competitionId}";
    String SAVE_COMPETITION = "/save";
    String ADD_PLAYER = "/add-player";

    List<Competition> getAllCompetitions();

    Competition getCompetitionById(Long competitionId);

    void addPlayer(AddPlayerRequest addPlayerRequest);

    /**
     * Endpoint saving the Competition to the database
     *
     * @param competition Model Competition description a competition
     */
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Save competition")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Will return MedDream access to"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"),
    })
    void saveCompetition(@RequestBody Competition competition);


}
