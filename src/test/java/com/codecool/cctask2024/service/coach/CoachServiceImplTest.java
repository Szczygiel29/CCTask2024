package com.codecool.cctask2024.service.coach;

import com.codecool.cctask2024.model.entity.Club;
import com.codecool.cctask2024.model.entity.Coach;
import com.codecool.cctask2024.model.entity.Player;
import com.codecool.cctask2024.model.repository.ClubRepository;
import com.codecool.cctask2024.model.repository.CoachRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link CoachServiceImpl} class.
 * It uses the Mockito framework and JUnit Jupiter's extensions for testing.
 * This class is responsible for providing test cases to ensure the proper functionality
 * of the {@link CoachServiceImpl} methods, which manage coach-related operations.
 */
@ExtendWith(MockitoExtension.class)
class CoachServiceImplTest {

    @InjectMocks
    private CoachServiceImpl coachService;

    @Mock
    private CoachRepository coachRepository;

    @Mock
    private ClubRepository clubRepository;

    @Test
    void saveCoach_ShouldSaveCoach_WhenValidCoach() {
        // Arrange
        Coach coach = new Coach();

        // Act
        coachService.saveCoach(coach);

        // Assert
        verify(coachRepository, times(1)).save(coach);
    }

    @Test
    void findAllCoaches_ShouldReturnListOfCoaches_WhenCoachesExist() {
        // Arrange
        List<Coach> mockCoaches = Arrays.asList(new Coach(), new Coach());
        when(coachRepository.findAll()).thenReturn(mockCoaches);

        // Act
        List<Coach> result = coachService.findAllCoaches();

        // Assert
        assertEquals(mockCoaches, result);
    }

    @Test
    void isPlayerAssignedToCoach_ShouldReturnTrue_WhenPlayerIsAssigned() {
        // Arrange
        Coach coach = new Coach();
        Player player = new Player();
        Club club = new Club();
        club.setPlayers(Set.of(player));

        when(clubRepository.findByCoach(coach)).thenReturn(Optional.of(club));

        // Act
        boolean result = coachService.isPlayerAssignedToCoach(coach, player);

        // Assert
        assertTrue(result);
    }

    @Test
    void isPlayerAssignedToCoach_ShouldReturnFalse_WhenPlayerIsNotAssigned() {
        // Arrange
        Coach coach = new Coach();
        Player player = new Player();
        Club club = new Club();
        club.setPlayers(new HashSet<>());

        when(clubRepository.findByCoach(coach)).thenReturn(Optional.of(club));

        // Act
        boolean result = coachService.isPlayerAssignedToCoach(coach, player);

        // Assert
        assertFalse(result);
    }

}