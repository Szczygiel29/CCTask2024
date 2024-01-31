package com.codecool.cctask2024.controller;


import com.codecool.cctask2024.controller.resources.CoachResource;
import com.codecool.cctask2024.model.entity.Coach;
import com.codecool.cctask2024.service.coach.CoachService;
import com.codecool.cctask2024.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.codecool.cctask2024.configuration.ApiConstraints.COACH;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(COACH)
public class CoachController implements CoachResource {

    private final CoachService coachService;

    @Override
    @PostMapping(value = SAVE_COACH)
    public void saveCoach(@RequestBody Coach coach) {
        coachService.saveCoach(coach);
        log.info("Coach was save successful: " + Utils.toJsonString(coach));
    }
}
