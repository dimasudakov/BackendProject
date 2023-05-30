package dev.dima.betservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@AllArgsConstructor
public class EventResponse {

    private final UUID id;

    private final UUID disciplineId;

    private final String country;

    private final String city;

    private final String competitionName;

    private final LocalDateTime startDate;

    private final boolean active;

    private final String competitor1Name;

    private final String competitor2Name;
}
