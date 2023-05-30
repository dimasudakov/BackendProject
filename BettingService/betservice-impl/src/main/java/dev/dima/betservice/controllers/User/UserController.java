package dev.dima.betservice.controllers.User;

import dev.dima.betservice.api.User.UserApi;
import dev.dima.betservice.dtos.requests.UserRequest;
import dev.dima.betservice.dtos.responses.BalanceResponse;
import dev.dima.betservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<String> createUser(@Valid UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok("User created");
    }

    @Override
    public ResponseEntity<Integer> getUserBalance(UUID userId) {
        return userService.getBalance(userId);
    }

    @Override
    public ResponseEntity<BalanceResponse> getUserBalanceWithCurrency(UUID userId) {
        return userService.getBalanceWithCurrency(userId);
    }
}
