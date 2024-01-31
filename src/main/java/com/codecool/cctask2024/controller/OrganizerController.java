package com.codecool.cctask2024.controller;


import com.codecool.cctask2024.controller.resources.OrganizerResource;
import com.codecool.cctask2024.model.entity.Organizer;
import com.codecool.cctask2024.service.organizer.OrganizerService;
import com.codecool.cctask2024.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.codecool.cctask2024.configuration.ApiConstraints.ORGANIZER;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(ORGANIZER)
public class OrganizerController implements OrganizerResource {

    private final OrganizerService organizerService;

    @Override
    @PostMapping(value = SAVE_ORGANIZER)
    public void saveOrganizer(@RequestBody Organizer organizer) {
        organizerService.saveOrganizer(organizer);
        log.info("Organize was save successfully: " + Utils.toJsonString(organizer));
    }
}
