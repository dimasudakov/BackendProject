package dev.dima.betservice.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DisciplineRequest {

    @NotBlank
    @Size(min = 3, max = 20, message = "Длина названия должна быть больше 3 и меньше 20")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z0-9 ]+$", message = "Название дисциплины может состоять только из букв и цифр")
    private String name;

}
