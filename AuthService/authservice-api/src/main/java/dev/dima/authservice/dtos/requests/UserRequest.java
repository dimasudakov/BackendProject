package dev.dima.authservice.dtos.requests;

import dev.dima.authservice.dtos.validation.Adult;
import dev.dima.authservice.dtos.validation.Password;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRequest {

    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "First name should contain only alphabetic characters")
    private final String firstName;

    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters long")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Last name should contain only alphabetic characters")
    private final String lastName;

    @Email(message = "Invalid Email")
    private final String email;

    @NotBlank(message = "Phone number mustn't be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
    private final String phoneNumber;

    @Password
    private final String password;

    @Past
    @Adult
    private final LocalDate dateOfBirth;
}
