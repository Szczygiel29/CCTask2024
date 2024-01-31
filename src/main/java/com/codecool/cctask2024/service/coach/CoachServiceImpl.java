package com.codecool.cctask2024.service.coach;


import com.codecool.cctask2024.exception.CCException;
import com.codecool.cctask2024.model.entity.Club;
import com.codecool.cctask2024.model.entity.Coach;
import com.codecool.cctask2024.model.entity.Player;
import com.codecool.cctask2024.model.repository.ClubRepository;
import com.codecool.cctask2024.model.repository.CoachRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CoachServiceImpl implements CoachService {

    CoachRepository coachRepository;
    ClubRepository clubRepository;

    @Override
    public void saveCoach(Coach coach) {
        try {
            coachRepository.save(coach);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Coach> findAllCoaches() {
        return coachRepository.findAll();
    }

    @Override
    public boolean isPlayerAssignedToCoach(Coach coach, Player player) {
        Club club = clubRepository.findByCoach(coach).orElseThrow(() -> new CCException(Club.class));
        return club.getPlayers().contains(player);
    }
}
