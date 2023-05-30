package dev.dima.betservice.dtos.responses;

import dev.dima.betservice.dtos.enums.DealType;
import dev.dima.betservice.dtos.enums.ResultType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class DealResponse {

    private final UUID id;

    private final DealType dealType;

    private final double money;

    private final double coefficient;

    private final LocalDateTime createdAt;

    private final ResultType status;

    private final List<BetResponse> bets;
}
