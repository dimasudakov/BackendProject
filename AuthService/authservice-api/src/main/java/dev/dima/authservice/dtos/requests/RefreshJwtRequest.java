package dev.dima.authservice.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshJwtRequest {

    @NotBlank
    public String refreshToken;

}
