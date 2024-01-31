/**
 * This package includes model for Player
 */
package com.codecool.cctask2024.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * This class contains the full data Player model, fields identifying the Player
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FullPlayerData {

    String firstName;
    String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date dateOfBirth;
    String textDate;
    String degree;
    String sex;
    String category;
    Long price;
    String typeOfPrice;
    Integer capacity;
}
