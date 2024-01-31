package com.codecool.cctask2024.service.ladder;

import com.codecool.cctask2024.model.enums.PlayerDegree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Custom {

    public static final Set<PlayerDegree> higherDegrees = new HashSet<>(Arrays.asList(PlayerDegree.BLUE, PlayerDegree.BROWN, PlayerDegree.BLACK));
    public static final Set<PlayerDegree> lowerDegrees = new HashSet<>(Arrays.asList(PlayerDegree.GREEN, PlayerDegree.YELLOW, PlayerDegree.WHITE));
    public static final String COMPETITION_LADDER_REDIS_KEY = "allLadders";
}