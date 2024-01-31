/**
 * This package includes implementation for DataStorage
 */
package com.codecool.cctask2024.service.organizer;


import com.codecool.cctask2024.model.entity.Organizer;
import com.codecool.cctask2024.model.repository.OrganizerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service contains methods saving data to database
 */
@Service
@Slf4j
@AllArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;

    @Override
    public void saveOrganizer(Organizer organizer) {
        try {
            organizerRepository.save(organizer);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    @Override
    public List<Organizer> findAllOrganizer() {
        return organizerRepository.findAll();
    }

}
