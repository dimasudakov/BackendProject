package dev.dima.transactionservice.services;

import dev.dima.transactionservice.dtos.requests.UserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void createUser(UserRequest userRequest);
}
