package dev.dima.betservice.dtos.requests;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class EventRequest2 {

    @NotBlank
    private final String disciplineName;

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

    @NotBlank
    private final String competitor1Name;

    @NotBlank
    private final String competitor2Name;

}


