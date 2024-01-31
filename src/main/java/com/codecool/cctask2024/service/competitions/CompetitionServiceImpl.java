package com.codecool.cctask2024.service.competitions;

import com.codecool.cctask2024.exception.CCException;
import com.codecool.cctask2024.model.entity.Competition;
import com.codecool.cctask2024.model.entity.Player;
import com.codecool.cctask2024.model.repository.CompetitionRepository;
import com.codecool.cctask2024.model.repository.PlayerRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codecool.cctask2024.exception.ExceptionMessages.COMPETITION_CAPACITY_EXCEEDED;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompetitionServiceImpl implements CompetitionService {

    CompetitionRepository competitionRepository;
    PlayerRepository playerRepository;

    /**
     * This method finding the all Competition Objects from database
     *
     * @return Returns all Competitions
     */
    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public Competition getCompetitionById(Long competitionId) {
        return competitionRepository.findById(competitionId)
                .orElseThrow(() -> new CCException(Competition.class, competitionId));
    }


    /**
     * This method saving the Competition model to database
     *
     * @param competition Model Competition description a competition
     */
    @Override
    public void createCompetition(Competition competition) {
        competition.setCapacity(8);
        competitionRepository.save(competition);
    }


    @Override
    public void addPlayer(Long competitionId, Long playerId) {
        var competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new CCException(Competition.class, competitionId));

        var player = playerRepository.findById(playerId)
                .orElseThrow(() -> new CCException(Player.class, playerId));

        validateCompetitionCapacity(competition);
        competition.getPlayers().add(player);
    }

    private void validateCompetitionCapacity(Competition competition) {
        if (competition.getPlayers().size() == competition.getCapacity()) {
            throw new CCException(COMPETITION_CAPACITY_EXCEEDED.getMessage());
        }
    }

}
