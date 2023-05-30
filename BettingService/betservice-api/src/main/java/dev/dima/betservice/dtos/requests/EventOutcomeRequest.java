package dev.dima.betservice.dtos.requests;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@Getter
@RequiredArgsConstructor
public class EventOutcomeRequest {

    @NotNull
    private UUID eventId;

    @NotBlank
    private String outcomeName;

    @DecimalMin(value = "1.0", inclusive = false, message = "Коэффициент должен быть строго больше 1")
    @DecimalMax(value = "50.0", message = "Коэффициент должен быть меньше 50")
    private double coefficient;
}
