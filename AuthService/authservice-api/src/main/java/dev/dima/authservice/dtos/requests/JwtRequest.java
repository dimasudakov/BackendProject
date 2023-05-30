package dev.dima.authservice.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
