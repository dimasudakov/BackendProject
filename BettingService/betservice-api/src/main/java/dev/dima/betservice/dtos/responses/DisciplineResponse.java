package dev.dima.betservice.dtos.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class DisciplineResponse {

    private final UUID id;

    private final String name;
}
