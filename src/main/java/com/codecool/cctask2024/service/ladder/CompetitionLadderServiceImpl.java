package com.codecool.cctask2024.service.ladder;

import com.codecool.cctask2024.exception.CCException;
import com.codecool.cctask2024.model.entity.Competition;
import com.codecool.cctask2024.model.entity.CompetitionLadder;
import com.codecool.cctask2024.model.entity.Player;
import com.codecool.cctask2024.model.enums.AgeCategory;
import com.codecool.cctask2024.model.enums.PlayerDegree;
import com.codecool.cctask2024.model.repository.CompetitionLadderRepository;
import com.codecool.cctask2024.model.repository.CompetitionRepository;
import com.codecool.cctask2024.model.repository.PlayerRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.codecool.cctask2024.exception.ExceptionMessages.*;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompetitionLadderServiceImpl implements CompetitionLadderService {

    CompetitionRepository competitionRepository;
    CompetitionLadderRepository competitionLadderRepository;
    PlayerRepository playerRepository;
    Cache<String, List<Player>> guavaCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build();

    @Override
    public List<CompetitionLadder> startCompetition(Long competitionId) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new CCException(Competition.class, competitionId));
        return createFirstRound(competition);
    }
    @Override
    public List<CompetitionLadder> startNextRound(Long competitionId) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new CCException(Competition.class, competitionId));
        Map<Integer, List<CompetitionLadder>> groupedByRound = competition.getCompetitionLadders().stream().collect(groupingBy(CompetitionLadder::getRound));
        Integer lastRound = Collections.max(groupedByRound.keySet());
        List<CompetitionLadder> lastRoundLadders = groupedByRound.get(lastRound);
        checkIfRoundIsOver(lastRoundLadders);
        checkIfLastRound(lastRoundLadders);
        List<Player> winners = lastRoundLadders.stream().map(CompetitionLadder::getWinner).collect(Collectors.toList());
        Integer initValueOfNextRound = 1;
        List<CompetitionLadder> competitionLadders = prepareNewRound(winners, lastRound + initValueOfNextRound, competition);
        return competitionLadderRepository.saveAll(competitionLadders);
    }

    @Override
    public List<CompetitionLadder> findLaddersForCompetition(Long competitionId) {
        return competitionLadderRepository.findAllCompetitionLadderByCompetitionId(competitionId);
    }
    @Override
    public CompetitionLadder setWinner(Long competitionId, Integer roundNumber, Long winnerId) {
        Player player = playerRepository.findById(winnerId).orElseThrow(() -> new CCException(Player.class, winnerId));
        CompetitionLadder competitionLadder = competitionLadderRepository.findCompetitionLadderByCompetitionIdAndRoundAndPlayerOneOrPlayerTwo(competitionId, roundNumber, player, player)
                .orElseThrow(() -> new CCException(Competition.class, competitionId));
        validateWinner(competitionLadder, winnerId);
        competitionLadder.setWinner(competitionLadder.getPlayerOne().getId().equals(winnerId) ?
                competitionLadder.getPlayerOne() : competitionLadder.getPlayerTwo());
        return competitionLadder;
    }

    private void validateWinner(CompetitionLadder competitionLadder, Long winnerId) {
        if (!competitionLadder.getPlayerOne().getId().equals(winnerId) &&
                !competitionLadder.getPlayerTwo().getId().equals(winnerId)) {
            throw new CCException(WINNER_MUST_BE_A_PLAYER.getMessage());
        }
    }

    private List<CompetitionLadder> createFirstRound(Competition competition) {
        groupPlayersByAgeCategoryAndAddToGuavaCache(competition.getPlayers());
        checkPlayersNumber(competition.getPlayers());
        List<List<Player>> playerListInAgeCategory = new ArrayList<>();
        playerListInAgeCategory.add(getPlayersFromGuavaCache(AgeCategory.SENIOR.getCategoryName()));
        playerListInAgeCategory.add(getPlayersFromGuavaCache(AgeCategory.JUNIOR.getCategoryName()));

        List<CompetitionLadder> allCompetitionLadders = new ArrayList<>();

        playerListInAgeCategory.forEach(players -> {
            prepareCompetitionLaddersForAgeCategories(competition, allCompetitionLadders, players);
        });

        return allCompetitionLadders;
    }

    private void groupPlayersByAgeCategoryAndAddToGuavaCache(List<Player> players) {
        players.forEach(player -> {
            String ageCategory = player.getAgeCategory().toString();
            List<Player> playersInCategory = guavaCache.getIfPresent(ageCategory);

            if (playersInCategory == null) {
                playersInCategory = Lists.newArrayList();
            }

            playersInCategory.add(player);
            guavaCache.put(ageCategory, playersInCategory);
        });
    }

    private List<Player> getPlayersFromGuavaCache(String ageCategory) {
        return guavaCache.getIfPresent(ageCategory);
    }

    private void prepareCompetitionLaddersForAgeCategories(Competition competition, List<CompetitionLadder> allCompetitionLadders, List<Player> players) {
        checkPlayersNumber(players);

        List<Player> higherDegreePlayers = getDegreePlayers(players, Custom.higherDegrees);
        List<Player> lowerDegreePlayers = getDegreePlayers(players, Custom.lowerDegrees);

        List<CompetitionLadder> higherDegreeCompetitionLadders = prepareFirstRound(higherDegreePlayers, competition);
        List<CompetitionLadder> lowerDegreeCompetitionLadders = prepareFirstRound(lowerDegreePlayers, competition);

        allCompetitionLadders.addAll(higherDegreeCompetitionLadders);
        allCompetitionLadders.addAll(lowerDegreeCompetitionLadders);
    }

    private List<CompetitionLadder> prepareNewRound(List<Player> players, Integer roundNumber, Competition competition) {
        List<CompetitionLadder> competitionLadders = new ArrayList<>();

        for (int i = 0; i <= players.size() - 1; i = i + 2) {
            Player playerOne = players.get(i);
            Player playerTwo = players.get(i + 1);

            competitionLadders.add(CompetitionLadder.builder().round(roundNumber)
                    .playerOne(playerOne).playerTwo(playerTwo).competition(competition).build());

        }
        return competitionLadders;
    }

    private List<CompetitionLadder> prepareFirstRound(List<Player> players, Competition competition) {
        List<CompetitionLadder> competitionLadders = new ArrayList<>();
        List<Player> remainingPlayers = new ArrayList<>(players);
        Integer roundNumber = 1;
        Random random = new Random();

        while (!remainingPlayers.isEmpty()) {
            Player playerOne = remainingPlayers.get(random.nextInt(remainingPlayers.size()));
            List<Player> availablePlayers = remainingPlayers.stream()
                    .filter(player -> !player.getClub().equals(playerOne.getClub()))
                    .toList();

            if (!availablePlayers.isEmpty()) {
                Player playerTwo = availablePlayers.get(random.nextInt(availablePlayers.size()));
                competitionLadders.add(
                        CompetitionLadder.builder().round(roundNumber).playerOne(playerOne)
                                .playerTwo(playerTwo).competition(competition).build()
                );
                remainingPlayers.remove(playerOne);
                remainingPlayers.remove(playerTwo);
            }
        }
        return competitionLadders;
    }

    private List<Player> getDegreePlayers(List<Player> players, Set<PlayerDegree> degrees) {
        return players.stream()
                .filter(player -> degrees.contains(player.getPlayerDegree()))
                .collect(Collectors.toList());
    }

    private void checkPlayersNumber(List<Player> players) {
        if (players.size() % 2 != 0) {
            throw new CCException(PLAYERS_NUMBER_MUST_BE_EVEN.getMessage());
        }
    }

    private void checkIfRoundIsOver(List<CompetitionLadder> competitionLadders) {
        for (CompetitionLadder step : competitionLadders) {
            if (isNull(step.getWinner())) {
                throw new CCException(COMPETITION_ROUND_IS_NOT_OVER_YET.getMessage());
            }
        }
    }

    private void checkIfLastRound(List<CompetitionLadder> competitionLadders) {
        if (competitionLadders.size() == 1) {
            throw new CCException(COMPETITION_HAS_LAST_ROUND_CANT_CREATE_NEW_ROUND.getMessage());
        }
    }
}
