package dev.dima.betservice.api.BettintManager;


import dev.dima.betservice.dtos.requests.EventRequest;
import dev.dima.betservice.dtos.requests.EventRequest2;
import dev.dima.betservice.dtos.responses.EventResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/betting/manager")
public interface EventsApi {

    @PostMapping("/events")
    ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest2 event);

    @GetMapping("/events/{eventId}")
    ResponseEntity<EventResponse> getEventById(@PathVariable UUID eventId);

    @DeleteMapping("/events/{eventId}")
    ResponseEntity<Void> deleteEvent(@PathVariable UUID eventId);

    @PutMapping("/events/{eventId}")
    ResponseEntity<EventResponse> updateEvent(@PathVariable UUID eventId,
                                              @Valid @RequestBody EventRequest event);

    @GetMapping("/events/by-discipline")
    ResponseEntity<List<EventResponse>> getEventsByDisciplineId(@RequestParam UUID disciplineId,
                                                                @RequestParam(required = false) LocalDate dateMin,
                                                                @RequestParam(required = false) LocalDate dateMax,
                                                                @RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "5") int eventsPerPage);

    @GetMapping("/events")
    ResponseEntity<List<EventResponse>> getEventsByDate(@RequestParam(required = false) LocalDate dateMin,
                                                        @RequestParam(required = false) LocalDate dateMax,
                                                        @RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "5") int eventsPerPage);
}
