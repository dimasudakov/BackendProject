package dev.dima.transactionservice.api;


import dev.dima.transactionservice.dtos.requests.UserRequest;
import dev.dima.transactionservice.dtos.responses.BalanceResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/transaction")
public interface BalanceApi {

    @PostMapping("/user/create")
    ResponseEntity<String> createUser(@Valid @RequestBody UserRequest userRequest);


    @GetMapping("/user/balance")
    ResponseEntity<BalanceResponse> getBalance(@AuthenticationPrincipal UUID userId);
}
