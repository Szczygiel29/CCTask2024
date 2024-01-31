/**
 * This package includes model for Competition
 */
package com.codecool.cctask2024.model.entity;

import com.codecool.cctask2024.configuration.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

import java.time.Instant;
import java.util.List;

/**
 * This class contains the Competition model, fields identifying the Competition
 */
@Entity
@Table(name = "club")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Competition extends BaseEntity {

    String name;
    String category;
    String sex;
    String vintage;
    Integer capacity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Instant dateOfStartCompetition;

    @JsonIgnore
    @OneToMany(mappedBy = "competition")
    List<CompetitionLadder> competitionLadders;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToMany
    @JoinTable(
            name = "competition_player",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    List<Player> players;
}