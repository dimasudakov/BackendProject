package dev.dima.betservice.api.BettintManager;


import dev.dima.betservice.dtos.requests.EventOutcomeRequest;
import dev.dima.betservice.dtos.requests.EventOutcomeResultRequest;
import dev.dima.betservice.dtos.responses.EventOutcomeResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/betting/manager")
public interface EventOutcomesApi {

    @PostMapping("/event-outcomes")
    ResponseEntity<EventOutcomeResponse> createEventOutcome(@Valid @RequestBody EventOutcomeRequest eventOutcomeRequest);

    @GetMapping("/event/event-outcomes/{eventId}")
    ResponseEntity<List<EventOutcomeResponse>> getEventOutcomeByEventId(@PathVariable UUID eventId);

    @DeleteMapping("/event-outcomes/{eventOutcomeId}")
    ResponseEntity<Void> deleteEventOutcome(@PathVariable UUID eventOutcomeId);

    @PutMapping("/event-outcomes/{eventOutcomeId}")
    ResponseEntity<EventOutcomeResponse> updateEventOutcome(@PathVariable UUID eventOutcomeId,
                                                            @Valid @RequestBody EventOutcomeRequest eventOutcomeRequest);

    @PutMapping("/event-outcomes/complete/{eventOutcomeId}")
    ResponseEntity<EventOutcomeResponse> completeEventOutcome(@PathVariable UUID eventOutcomeId,
                                                              @Valid @RequestBody EventOutcomeResultRequest eventOutcomeResultRequest);

    @GetMapping("/event-outcomes/{eventOutcomeId}")
    ResponseEntity<EventOutcomeResponse> getEventOutcomeById(@PathVariable UUID eventOutcomeId);
}
