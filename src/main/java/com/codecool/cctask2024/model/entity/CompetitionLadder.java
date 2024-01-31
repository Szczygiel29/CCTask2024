/**
 * This package includes model for Competition
 */
package com.codecool.cctask2024.model.entity;

import com.codecool.cctask2024.configuration.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * This class contains the CompetitionLadder model, fields identifying the competition ladder
 */
@Entity
@Table(name = "competition_ladder")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompetitionLadder extends BaseEntity implements Serializable {

    Integer round;
    @JsonIgnore
    @ManyToOne
    Player playerOne;
    @JsonIgnore
    @ManyToOne
    Player playerTwo;
    @JsonIgnore
    @ManyToOne
    Player winner;

    @ManyToOne
    Competition competition;
}
