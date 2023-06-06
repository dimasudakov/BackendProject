package dev.dima.betservice.controllers.BettingManager;

import dev.dima.betservice.api.BettintManager.EventsApi;
import dev.dima.betservice.dtos.requests.EventRequest;
import dev.dima.betservice.dtos.requests.EventRequest2;
import dev.dima.betservice.dtos.responses.EventResponse;
import dev.dima.betservice.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
public class EventsController implements EventsApi {

    private final EventService eventService;


    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<EventResponse> createEvent(@Valid EventRequest2 event) {
        return eventService.createEvent(event);
    }

    @Override
    public ResponseEntity<EventResponse> getEventById(UUID eventId) {
        return eventService.getEventById(eventId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<Void> deleteEvent(UUID eventId) {
        return eventService.deleteEventById(eventId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<EventResponse> updateEvent(UUID eventId, @Valid EventRequest eventRequest) {
        return eventService.updateEvent(eventId, eventRequest);
    }

    @Override
    public ResponseEntity<List<EventResponse>> getEventsByDisciplineId(UUID disciplineId, LocalDate dateMin, LocalDate dateMax, int page, int eventsPerPage) {
        if(dateMin == null) {
            dateMin = LocalDate.now();
        }
        if(dateMax == null) {
            dateMax = LocalDate.of(2100, 1, 1);
        }
        return eventService.getEventsByDisciplineId(disciplineId, dateMin, dateMax, page, eventsPerPage);
    }

    @Override
    public ResponseEntity<List<EventResponse>> getEventsByDate(LocalDate dateMin, LocalDate dateMax, int page, int eventsPerPage) {
        if(dateMin == null) {
            dateMin = LocalDate.now();
        }
        if(dateMax == null) {
            dateMax = LocalDate.of(2100, 1, 1);
        }

        return eventService.getEventsByDate(dateMin, dateMax, page, eventsPerPage);
    }

}
