package dev.dima.transactionservice.controllers;

import com.hazelcast.core.HazelcastInstance;
import dev.dima.transactionservice.api.BalanceApi;
import dev.dima.transactionservice.dtos.requests.UserRequest;
import dev.dima.transactionservice.dtos.responses.BalanceResponse;
import dev.dima.transactionservice.services.BalanceService;
import dev.dima.transactionservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class BalanceController implements BalanceApi {

    private final BalanceService balanceService;

    private final UserService userService;

    @Override
    public ResponseEntity<String> createUser(@Valid UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok("User created");
    }

    @Override
    public ResponseEntity<BalanceResponse> getBalance(UUID userId) {
        int balance = balanceService.getUserBalance(userId);
        BalanceResponse balanceResponse = new BalanceResponse(userId, balance);
        return ResponseEntity.ok(balanceResponse);
    }
}
