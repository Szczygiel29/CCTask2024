package com.codecool.cctask2024.model.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddPlayerRequest {
    private Long competitionId;
    private Long playerId;
}
