package dev.dima.authservice.services;

import dev.dima.authservice.dtos.requests.JwtRequest;
import dev.dima.authservice.dtos.requests.UserRequest;
import dev.dima.authservice.dtos.responses.JwtResponse;
import dev.dima.authservice.models.JwtAuthentication;
import jakarta.validation.Valid;
import lombok.NonNull;

public interface AuthService {

    void register(@Valid UserRequest userDto);

    JwtResponse login(@NonNull JwtRequest authRequest);

    JwtResponse getAccessToken(@NonNull String refreshToken);

    JwtResponse refresh(@NonNull String refreshToken);

    JwtAuthentication getAuthInfo();
}
