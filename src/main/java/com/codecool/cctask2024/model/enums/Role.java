package com.codecool.cctask2024.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role{
    COACH("ROLE_COACH"),
    ADMIN("ROLE_ADMIN"),
    ORGANIZER("ROLE_ORGANIZER");

    private final String roleName;
}
