package dev.dima.authservice.controllers;

import dev.dima.authservice.api.AuthApi;
import dev.dima.authservice.dtos.requests.JwtRequest;
import dev.dima.authservice.dtos.requests.RefreshJwtRequest;
import dev.dima.authservice.dtos.requests.UserRequest;
import dev.dima.authservice.dtos.responses.JwtResponse;
import dev.dima.authservice.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<String> register(@RequestBody @Valid UserRequest userDto) {
        authService.register(userDto);
        return ResponseEntity.ok("User created");
    }
    @Override
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}
