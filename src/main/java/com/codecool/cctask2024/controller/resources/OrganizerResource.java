package com.codecool.cctask2024.controller.resources;


import com.codecool.cctask2024.model.entity.Organizer;

public interface OrganizerResource {

    String SAVE_ORGANIZER = "/save-organizer";

    void saveOrganizer(Organizer organizer);
}
