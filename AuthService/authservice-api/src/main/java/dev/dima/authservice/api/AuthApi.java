package dev.dima.authservice.api;

import dev.dima.authservice.dtos.requests.JwtRequest;
import dev.dima.authservice.dtos.requests.RefreshJwtRequest;
import dev.dima.authservice.dtos.requests.UserRequest;
import dev.dima.authservice.dtos.responses.JwtResponse;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public interface AuthApi {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<String> register(@RequestBody @Valid UserRequest user) throws AuthException;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<JwtResponse> login(@RequestBody @Valid JwtRequest authRequest) throws AuthException;


    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody @Valid RefreshJwtRequest request) throws AuthException;


    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody @Valid RefreshJwtRequest request) throws AuthException;

}
