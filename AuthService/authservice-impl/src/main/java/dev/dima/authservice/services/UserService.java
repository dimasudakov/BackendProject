package dev.dima.authservice.services;

import dev.dima.authservice.dtos.responses.UserResponse;
import dev.dima.authservice.exceptions.UserNotFoundException;
import dev.dima.authservice.models.UserEntity;
import dev.dima.authservice.models.enums.Status;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    Optional<UserEntity> getByEmail(@NotBlank String login);

    Optional<UserEntity> getByPhoneNumber(@NotBlank String phoneNumber);

    Optional<UserEntity> createUser(@NotBlank UserEntity userEntity);

    @Override
    UserDetails loadUserByUsername(String username) throws UserNotFoundException;

    Optional<UserEntity> getById(@NotBlank UUID userID);

    UserResponse getUserFromToken();

    void changeStatus(UUID id, Status newStatus);

    void deleteUser(UserEntity userEntity);
}
