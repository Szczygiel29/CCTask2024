/**
 * This package includes methods saving data to database
 */
package com.codecool.cctask2024.service.player;


import com.codecool.cctask2024.model.entity.FullPlayerData;
import com.codecool.cctask2024.model.entity.Player;

import java.util.List;

/**
 * Interface contains methods saving data to database
 */
public interface PlayerService {
    /**
     * This method saving the Player model to database
     *
     * @param fullPlayerData Model Full Data Player description a player
     */
    void saveFullDataPlayer(FullPlayerData fullPlayerData);

    /**
     * This method finding the all Player Objects from database
     *
     * @return Returns all Players
     */
    List<Player> findAllPlayers();

    void savePlayer(Player player);
}
