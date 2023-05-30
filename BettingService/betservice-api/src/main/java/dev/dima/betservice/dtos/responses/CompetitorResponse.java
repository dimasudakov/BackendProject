package dev.dima.betservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CompetitorResponse {

    private final UUID id;

    private final String name;

    private final String country;

    private final UUID disciplineId;

}
