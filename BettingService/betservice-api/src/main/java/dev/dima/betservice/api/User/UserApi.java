package dev.dima.betservice.api.User;

import dev.dima.betservice.dtos.requests.UserRequest;
import dev.dima.betservice.dtos.responses.BalanceResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/betting")
public interface UserApi {

    @PostMapping("/user/create")
    ResponseEntity<String> createUser(@Valid @RequestBody UserRequest userRequest);

    @GetMapping("/user/balance")
    ResponseEntity<Integer> getUserBalance(@AuthenticationPrincipal UUID userId);

    @GetMapping("/user/balance/currency")
    ResponseEntity<BalanceResponse> getUserBalanceWithCurrency(@AuthenticationPrincipal UUID userId);
}
