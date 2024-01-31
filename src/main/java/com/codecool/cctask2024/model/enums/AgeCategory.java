package com.codecool.cctask2024.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgeCategory {
    JUNIOR("junior", 6),
    SENIOR("senior", 17);

    private final String categoryName;
    private final int minValue;
}