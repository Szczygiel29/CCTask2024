package com.codecool.cctask2024.controller.resources;

import com.codecool.cctask2024.model.entity.Coach;

public interface CoachResource {

    String SAVE_COACH = "/save-coach";

    void saveCoach(Coach coach);
}
