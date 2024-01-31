/**
 * This package includes model for Player
 */
package com.codecool.cctask2024.model.entity;

import com.codecool.cctask2024.configuration.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * This class contains the Organizer model, fields identifying the Organizer
 */
@Entity
@Table(name = "organizer")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Organizer extends BaseEntity {

    String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endDate;
    File picture;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany
    List<Club> clubs;

    @OneToMany
    List<Competition> competitions;
}
