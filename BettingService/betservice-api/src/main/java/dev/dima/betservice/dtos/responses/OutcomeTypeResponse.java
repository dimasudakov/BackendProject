package dev.dima.betservice.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class OutcomeTypeResponse {

    private final UUID id;

    private final String type;
}
