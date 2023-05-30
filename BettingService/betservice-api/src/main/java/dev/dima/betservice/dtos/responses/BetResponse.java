package dev.dima.betservice.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class BetResponse {

    private final UUID id;

    private final UUID dealId;

    private final EventOutcomeResponse eventOutcome;

    private final double coefficient;

}
