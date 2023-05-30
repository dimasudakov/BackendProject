package dev.dima.betservice.dtos.requests;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class EventRequest {

    @NotNull
    private final UUID disciplineId;

    @NotBlank
    @Size(min = 3, max = 20, message = "Длина названия должна быть больше 3 и меньше 40")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z ]+$", message = "Название страны может состоять только из букв")
    private final String country;

    @NotBlank
    @Size(min = 3, max = 20, message = "Длина названия должна быть больше 3 и меньше 40")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z ]+$", message = "Название города может состоять только из букв")
    private final String city;

    @NotBlank
    @Size(min = 3, max = 40, message = "Длина названия должна быть больше 3 и меньше 40")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z0-9 ]+$", message = "Название соревнования может состоять только из букв и цифр")
    private final String competitionName;

    @Future(message = "Событие должно произойти в будущем")
    private final LocalDateTime startDate;

    @NotNull
    private final UUID competitor1Id;

    @NotNull
    private final UUID competitor2Id;

}


