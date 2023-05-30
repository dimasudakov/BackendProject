package dev.dima.authservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;

}