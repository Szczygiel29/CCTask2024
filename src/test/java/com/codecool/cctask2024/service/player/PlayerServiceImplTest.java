package com.codecool.cctask2024.service.player;

import com.codecool.cctask2024.model.entity.Player;
import com.codecool.cctask2024.model.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link PlayerServiceImpl} class.
 * These tests focus on verifying the behavior of methods in the {@link PlayerServiceImpl} implementation.
 * Mockito is used to mock dependencies and isolate the tested class.
 */
@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepository;

    /**
     * This test focuses on verifying the behavior of saving a player.
     */
    @Test
    void save_player() {
        //Given
        Player player = new Player();
        when(playerRepository.save(player)).thenReturn(player);

        //When
        playerService.savePlayer(player);

        //Then
        verify(playerRepository).save(player);
    }

    /**
     * This test focuses on verifying the behavior of saving a player with specific scenario.
     */
    @Test
    void save_empty_player() {
        //Given
        Player player = new Player();
        player.setFirstName("test");
        player.setLastName("test");
        player.setDegree("test");
        player.setSex("male");

        when(playerRepository.save(player)).thenReturn(player);

        //When
        playerService.savePlayer(player);

        //Then
        verify(playerRepository).save(player);
    }

    /**
     * This test focuses on verifying the behavior of finding all players.
     */
    @Test
    void get_all_players() {
        //Given
        Player player1 = new Player();
        Player player2 = new Player();

        when(playerRepository.findAll()).thenReturn(List.of(player1, player2));

        //When
        List<Player> players = playerService.findAllPlayers();

        //Then
        verify(playerRepository).findAll();
        assertEquals(2, players.size());
        assertEquals(player1, players.get(0));
        assertEquals(player2, players.get(1));
    }

    /**
     * This test focuses on verifying the behavior of finding all players when the list is empty.
     */
    @Test
    void get_all_players_empty_list() {
        //Given
        when(playerRepository.findAll()).thenReturn(Collections.emptyList());

        //When
        List<Player> players = playerService.findAllPlayers();

        //Then
        verify(playerRepository).findAll();
        assertTrue(players.isEmpty());
    }
}