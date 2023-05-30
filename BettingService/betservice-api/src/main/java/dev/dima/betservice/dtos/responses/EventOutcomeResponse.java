package dev.dima.betservice.dtos.responses;

import dev.dima.betservice.dtos.enums.ResultType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class EventOutcomeResponse {

    private final UUID id;

    private final UUID eventId;

    private final String eventName;

    private final String outcomeType;

    private final double coefficient;

    private final ResultType status;
}
