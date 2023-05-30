package dev.dima.authservice.controllers;

import dev.dima.authservice.api.AdminApi;
import dev.dima.authservice.models.enums.Status;
import dev.dima.authservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController implements AdminApi {

    private final UserService userService;
    @Override
    public ResponseEntity<String> blockUser(UUID id) {
        userService.changeStatus(id, Status.BLOCKED);
        return ResponseEntity.ok("User with id: " + id + " blocked");
    }

    @Override
    public ResponseEntity<String> unblockUser(UUID id) {
        userService.changeStatus(id, Status.ACTIVE);
        return ResponseEntity.ok("User with id: " + id + " unlocked");
    }

}
