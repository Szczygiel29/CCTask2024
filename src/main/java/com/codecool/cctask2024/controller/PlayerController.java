package com.codecool.cctask2024.controller;


import com.codecool.cctask2024.controller.resources.PlayerResource;
import com.codecool.cctask2024.model.entity.FullPlayerData;
import com.codecool.cctask2024.model.entity.Player;
import com.codecool.cctask2024.service.player.PlayerService;
import com.codecool.cctask2024.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.codecool.cctask2024.configuration.ApiConstraints.PLAYER;


/**
 * This class contains endpoints for Publisher Application
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(PLAYER)
public class PlayerController implements PlayerResource {

    private final PlayerService playerService;

    @GetMapping(value = FIND_ALL_PLAYERS)
    public List<Player> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    @PostMapping(value = SAVE_PLAYER)
    public void saveFullDataPlayer(@RequestBody FullPlayerData fullPlayerData) {
        playerService.saveFullDataPlayer(fullPlayerData);
        log.info("Player full data was stored successfully: " + Utils.toJsonString(fullPlayerData));
    }
}
