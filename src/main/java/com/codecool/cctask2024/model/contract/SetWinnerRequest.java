package com.codecool.cctask2024.model.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetWinnerRequest {
    private Long competitionId;
    private Integer roundNumber;
    private Long winnerId;
}
