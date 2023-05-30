package dev.dima.betservice.controllers.BettingManager;

import dev.dima.betservice.api.BettintManager.EventOutcomesApi;
import dev.dima.betservice.dtos.requests.EventOutcomeRequest;
import dev.dima.betservice.dtos.requests.EventOutcomeResultRequest;
import dev.dima.betservice.dtos.responses.EventOutcomeResponse;
import dev.dima.betservice.services.EventOutcomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
public class EventOutcomesController implements EventOutcomesApi {

    private final EventOutcomeService eventOutcomeService;

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<EventOutcomeResponse> createEventOutcome(@Valid EventOutcomeRequest eventOutcomeRequest) {
        return eventOutcomeService.createEventOutcome(eventOutcomeRequest);
    }

    @Override
    public ResponseEntity<List<EventOutcomeResponse>> getEventOutcomeByEventId(UUID eventId) {
        return eventOutcomeService.getEventOutcomeByEventId(eventId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<Void> deleteEventOutcome(UUID eventOutcomeId) {
        return eventOutcomeService.deleteOutcome(eventOutcomeId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<EventOutcomeResponse> updateEventOutcome(UUID eventOutcomeId, @Valid EventOutcomeRequest eventOutcomeRequest) {
        return eventOutcomeService.updateOutcome(eventOutcomeId, eventOutcomeRequest);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<EventOutcomeResponse> completeEventOutcome(UUID eventOutcomeId, @Valid EventOutcomeResultRequest result) {
        return eventOutcomeService.completeOutcome(eventOutcomeId, result);
    }

    @Override
    public ResponseEntity<EventOutcomeResponse> getEventOutcomeById(UUID eventOutcomeId) {
        return eventOutcomeService.getEventOutcomeById(eventOutcomeId);
    }
}
