package dev.dima.betservice.services;


import dev.dima.betservice.dtos.requests.UserRequest;
import dev.dima.betservice.dtos.responses.BalanceResponse;
import dev.dima.betservice.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    void createUser(UserRequest userRequest);

    void updateUser(User user);

    ResponseEntity<Integer> getBalance(UUID userId);

    ResponseEntity<BalanceResponse> getBalanceWithCurrency(UUID userId);
}
