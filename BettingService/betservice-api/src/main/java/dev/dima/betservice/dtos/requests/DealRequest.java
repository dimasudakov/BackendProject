package dev.dima.betservice.dtos.requests;

import dev.dima.betservice.dtos.enums.DealType;
import dev.dima.betservice.dtos.validation.CorrectBets;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;


@Getter
@RequiredArgsConstructor
public class DealRequest {

    @NotNull
    private final DealType dealType;

    @Min(value = 50, message = "Ставка должна быть больше 50 рублей")
    @Max(value = 50000, message = "Ставка должна быть меньше 50000 рублей")
    private final int money;

    @DecimalMin(value = "1.0", inclusive = false, message = "Коэффициент должен быть строго больше 1")
    @DecimalMax(value = "100.0", message = "Коэффициент должен быть меньше 100")
    private final double coefficient;


    @CorrectBets
    private final List<UUID> bets;
}
