package dev.dima.betservice.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompetitorRequest {

    @NotBlank
    @Size(min = 3, max = 40, message = "Длина названия должна быть больше 3 и меньше 40")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z0-9 ]+$", message = "Название участника или команды может состоять только из букв и цифр")
    private String name;

    @NotBlank
    @Size(min = 3, max = 40, message = "Длина названия должна быть больше 3 и меньше 40")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z ]+$", message = "Название страны может состоять только из букв")
    private String country;

    @NotNull
    private UUID disciplineId;
}
