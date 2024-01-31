package com.codecool.cctask2024.controller.resources;

import com.codecool.cctask2024.model.entity.FullPlayerData;
import com.codecool.cctask2024.model.entity.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface PlayerResource {

    String FIND_ALL_PLAYERS = "/all";
    String SAVE_PLAYER = "/save";

    List<Player> getAllPlayers();

    @PostMapping(value = "/save/player")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Save player")
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
    void saveFullDataPlayer(@RequestBody FullPlayerData fullPlayerData);
}
