package com.codecool.cctask2024.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessages {

    PLAYERS_NUMBER_MUST_BE_EVEN("Cannot start the competition, the number of participants is not even"),
    WINNER_MUST_BE_A_PLAYER("The player with this ID did not participate in the match, cannot declare them as the winner"),
    JSON_STRING_GENERATING_EXCEPTION("An issue occurred while converting the object into a JSON string"),
    COMPETITION_CAPACITY_EXCEEDED("Cannot add another player, the competition has reached the maximum number of participants"),
    COMPETITION_ROUND_IS_NOT_OVER_YET("The round has not ended yet. Ending a round requires a winner from each match"),
    COMPETITION_HAS_LAST_ROUND_CANT_CREATE_NEW_ROUND("There is already the last round, cannot create a new one"),
    COACH_NOT_FOUND_BY_USERNAME("No user found with this username"),
    USER_NOT_FOUND_BY_USERNAME("No user found with this username");

    private final String message;
}
