package dev.dima.authservice.services;

import dev.dima.authservice.dtos.responses.UserResponse;
import dev.dima.authservice.exceptions.StatusNotChangedException;
import dev.dima.authservice.exceptions.UserNotFoundException;
import dev.dima.authservice.models.JwtAuthentication;
import dev.dima.authservice.models.UserEntity;
import dev.dima.authservice.models.UserRole;
import dev.dima.authservice.models.enums.Role;
import dev.dima.authservice.models.enums.Status;
import dev.dima.authservice.repositories.UserRepository;
import dev.dima.authservice.utils.mappers.UserMapper;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserEntity> getByEmail(@NotBlank String login) {
        return userRepository.findByEmail(login);
    }

    public Optional<UserEntity> getByPhoneNumber(@NotBlank String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<UserEntity> createUser(@NotBlank UserEntity userEntity) {
        if(userEntity.getUserRoles() == null || userEntity.getUserRoles().size() == 0) {
            UserRole userRole = new UserRole();
            userRole.setRole(Role.ROLE_USER);
            userRole.setUser(userEntity);

            userEntity.setRole(userRole);
            userEntity.setStatus(Status.ACTIVE);
        }
        return Optional.of(userRepository.save(userEntity));
    }

    @Override
    public UserDetails loadUserByUsername(@NotBlank String login) throws UserNotFoundException {
        return userRepository.findByEmailOrPhoneNumber(login)
                .orElseThrow(() -> new UserNotFoundException("User with username " + login + " not found"));
    }

    public Optional<UserEntity> getById(@NotBlank UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserResponse getUserFromToken() {
        JwtAuthentication jwtAuthentication = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        UUID userId = jwtAuthentication.getUserId();
        UserEntity user = getById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with given ID not found"));

        return userMapper.toResponse(user);
    }

    @Override
    public void changeStatus(@NotBlank UUID id, Status newStatus) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        if(user.getStatus() == newStatus) {
            throw new StatusNotChangedException("User is already " + newStatus.getValue());
        }

        user.setStatus(newStatus);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

}
