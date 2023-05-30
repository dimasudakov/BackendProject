package dev.dima.authservice.controllers;

import dev.dima.authservice.api.UserApi;
import dev.dima.authservice.dtos.responses.UserResponse;
import dev.dima.authservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> getUser() {
        UserResponse userResponse = userService.getUserFromToken();
        return ResponseEntity.ok(userResponse);
    }
}
