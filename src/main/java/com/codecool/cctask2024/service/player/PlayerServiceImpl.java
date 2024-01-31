/**
 * This package includes implementation for DataStorage
 */
package com.codecool.cctask2024.service.player;


import com.codecool.cctask2024.model.entity.Competition;
import com.codecool.cctask2024.model.entity.FullPlayerData;
import com.codecool.cctask2024.model.entity.Player;
import com.codecool.cctask2024.model.repository.CompetitionRepository;
import com.codecool.cctask2024.model.repository.PlayerRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service contains methods saving data to database
 */
@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerServiceImpl implements PlayerService {

    PlayerRepository playerRepository;
    CompetitionRepository competitionRepository;

    /**
     * This method saves the Player model to database
     *
     * @param fullPlayerData Model Full Data Player description a player
     */
    @Override
    public void saveFullDataPlayer(FullPlayerData fullPlayerData) {
        Player player = Player.builder()
                .sex(fullPlayerData.getSex())
                .degree(fullPlayerData.getDegree())
                .firstName(fullPlayerData.getFirstName())
                .lastName(fullPlayerData.getLastName())
                .degree(fullPlayerData.getDegree())
                .dateOfBirth(fullPlayerData.getDateOfBirth())
                .build();

        Competition competition = Competition.builder()
                .name(fullPlayerData.getFirstName())
                .category(fullPlayerData.getCategory())
                .capacity(fullPlayerData.getCapacity())
                .build();
        try {
            playerRepository.save(player);
            competitionRepository.save(competition);
            log.info("Saved full data player");
        } catch (Exception ex) {
            log.error("Save was fail for full data player error: " + ex.getMessage());
        }
    }

    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void savePlayer(Player player) {
        try {
            playerRepository.save(player);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
