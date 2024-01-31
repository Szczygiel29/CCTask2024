/**
 * This package includes methods saving data to database
 */
package com.codecool.cctask2024.service.organizer;


import com.codecool.cctask2024.model.entity.Organizer;

import java.util.List;

/**
 * Interface contains methods saving data to database
 */
public interface OrganizerService {
    /**
     * This method saving the Organizer model to database
     *
     * @param organizer Model Organizer description a organizer
     */
    void saveOrganizer(Organizer organizer);

    List<Organizer> findAllOrganizer();

}
