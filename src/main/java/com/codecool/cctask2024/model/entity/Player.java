/**
 * This package includes model for Player
 */
package com.codecool.cctask2024.model.entity;


import com.codecool.cctask2024.configuration.BaseEntity;
import com.codecool.cctask2024.model.enums.AgeCategory;
import com.codecool.cctask2024.model.enums.PlayerDegree;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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

import java.io.Serializable;
import java.util.Date;

/**
 * This class contains the Player model, fields identifying the Player
 */
@Entity
@Table(name = "player")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Player extends BaseEntity implements Serializable {

    String firstName;
    String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date dateOfBirth;
    String textDate;
    String degree;
    String sex;
    PlayerDegree playerDegree;
    AgeCategory ageCategory;

    @OneToOne
    Coach coach;

    @ManyToOne
    Club club;
}
