package com.codecool.cctask2024.service.competitions;

import com.codecool.cctask2024.exception.CCException;
import com.codecool.cctask2024.model.entity.Competition;
import com.codecool.cctask2024.model.entity.Player;
import com.codecool.cctask2024.model.repository.CompetitionRepository;
import com.codecool.cctask2024.model.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the {@link CompetitionServiceImpl} class.
 * It uses the Mockito framework and JUnit Jupiter's extensions for testing.
 * These tests focus on testing individual methods of the {@link CompetitionServiceImpl} class
 * in isolation, by mocking external dependencies and verifying the behavior of the service.
 */
@ExtendWith(MockitoExtension.class)
class CompetitionServiceImplTest {

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private PlayerRepository playerRepository;


    /**
     * This test focuses on verifying the behavior of retrieving all competitions.
     */
    @Test
    void get_all_competitions() {
        //Given
        Competition competition1 = new Competition();
        Competition competition2 = new Competition();
        when(competitionRepository.findAll()).thenReturn(List.of(competition1, competition2));

        //When
        List<Competition> competitions = competitionService.getAllCompetitions();

        //Then
        assertEquals(2, competitions.size());
    }

    /**
     * This test focuses on verifying the behavior of retrieving by id competitions.
     */
    @Test
    void get_competitions_by_Id() {
        //Given
        Competition competition = new Competition();
        Long competitionId = 1234L;
        competition.setId(competitionId);
        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(competition));

        //When
        Competition retrievedCompetition = competitionService.getCompetitionById(competitionId);

        //Then
        assertNotNull(retrievedCompetition);
        assertEquals(competition.getId(), retrievedCompetition.getId());
    }

    /**
     * This test focuses on verifying the behavior of creating competitions.
     */
    @Test
    void create_competition() {
        //Given
        Competition competition = new Competition();
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);

        //When
        competitionService.createCompetition(competition);

        //Then
        verify(competitionRepository).save(any(Competition.class));
    }

    /**
     * This test focuses on verifying the behavior of adding player to competitions.
     */
    @Test
    void add_player() {
        //Given
        Competition competition = new Competition();
        competition.setCapacity(2);
        List<Player> players = new ArrayList<>();
        competition.setPlayers(players);

        Player player = new Player();

        when(competitionRepository.findById(competition.getId())).thenReturn(Optional.of(competition));
        when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));


        //When
        competitionService.addPlayer(competition.getId(), player.getId());

        //Then
        verify(competitionRepository).findById(competition.getId());
        verify(playerRepository).findById(player.getId());
        assertEquals(1, competition.getPlayers().size());
        assertEquals(player, competition.getPlayers().get(0));
    }

    /**
     * This test focuses on verifying the behavior of adding a player to a competition
     * when the competition's player capacity has been reached.
     */
    @Test
    void add_player_when_competition_capacity_exceeded() {
        // Given
        Competition competition = new Competition();
        competition.setCapacity(1);
        List<Player> players = new ArrayList<>();
        Player existingPlayer = new Player();
        players.add(existingPlayer);
        competition.setPlayers(players);

        Player newPlayer = new Player();


        when(competitionRepository.findById(competition.getId())).thenReturn(Optional.of(competition));
        when(playerRepository.findById(newPlayer.getId())).thenReturn(Optional.of(newPlayer));


        // When and Then
        assertThrows(CCException.class, () -> competitionService.addPlayer(competition.getId(), newPlayer.getId()));
        verify(competitionRepository).findById(competition.getId());
        verify(playerRepository).findById(newPlayer.getId());
        assertEquals(1, competition.getPlayers().size());
    }
}