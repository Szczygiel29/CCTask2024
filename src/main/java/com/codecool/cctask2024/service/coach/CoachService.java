package com.codecool.cctask2024.service.coach;


import com.codecool.cctask2024.model.entity.Coach;
import com.codecool.cctask2024.model.entity.Player;

import java.util.List;

public interface CoachService {
    void saveCoach(Coach coach);

    List<Coach> findAllCoaches();

    boolean isPlayerAssignedToCoach(Coach coach, Player player);
}
