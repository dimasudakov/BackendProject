package dev.dima.betservice.dtos.requests;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class BetRequest {

    @NotNull
    private final UUID dealId;

    @NotNull
    private final UUID eventOutcomeId;

    @DecimalMin(value = "1.0", inclusive = false, message = "Коэффициент должен быть строго больше 1")
    @DecimalMax(value = "20.0", message = "Коэффициент должен быть меньше 20")
    private final double coefficient;
}
