package dev.dima.betservice.services;

import dev.dima.betservice.dtos.requests.EventOutcomeRequest;
import dev.dima.betservice.dtos.requests.EventOutcomeResultRequest;
import dev.dima.betservice.dtos.responses.EventOutcomeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface EventOutcomeService {

    ResponseEntity<EventOutcomeResponse> createEventOutcome(EventOutcomeRequest eventOutcomeRequest);

    ResponseEntity<List<EventOutcomeResponse>> getEventOutcomeByEventId(UUID eventOutcomeId);

    ResponseEntity<Void> deleteOutcome(UUID eventOutcomeId);

    ResponseEntity<EventOutcomeResponse> updateOutcome(UUID eventOutcomeId, EventOutcomeRequest eventOutcomeRequest);

    ResponseEntity<EventOutcomeResponse> getEventOutcomeById(UUID eventOutcomeId);

    ResponseEntity<EventOutcomeResponse> completeOutcome(UUID eventOutcomeId, EventOutcomeResultRequest result);
}
