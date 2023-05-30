package dev.dima.betservice.controllers.Admin;

import dev.dima.betservice.api.AdminApi;
import dev.dima.betservice.services.BetService;
import dev.dima.betservice.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController implements AdminApi {

    private final EventService eventService;

    private final BetService betService;

    @Override

    public ResponseEntity<String> blockEvent(UUID eventId) {
        eventService.blockEvent(eventId);
        return ResponseEntity.ok("Event blocked");
    }

    @Override
    public ResponseEntity<String> unblockEvent(UUID eventId) {
        eventService.unblockEvent(eventId);
        return ResponseEntity.ok("Event unblocked");
    }

    @Override
    public ResponseEntity<String> blockBet(UUID betId) {
        betService.blockBet(betId);
        return ResponseEntity.ok("Outcome blocked");
    }

    @Override
    public ResponseEntity<String> unblockBet(UUID betId) {
        betService.unblockBet(betId);
        return ResponseEntity.ok("Outcome unblocked");
    }
}
