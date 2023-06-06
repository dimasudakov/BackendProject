package dev.dima.betservice.services;

import dev.dima.betservice.dtos.requests.EventRequest;
import dev.dima.betservice.dtos.requests.EventRequest2;
import dev.dima.betservice.dtos.responses.EventResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface EventService {
    void blockEvent(UUID eventId);

    void unblockEvent(UUID eventId);

    ResponseEntity<EventResponse> createEvent(EventRequest event);
    ResponseEntity<EventResponse> createEvent(EventRequest2 event);

    ResponseEntity<EventResponse> getEventById(UUID eventId);

    ResponseEntity<Void> deleteEventById(UUID eventId);

    ResponseEntity<EventResponse> updateEvent(UUID eventId, EventRequest event);

    ResponseEntity<List<EventResponse>> getEventsByDisciplineId(UUID disciplineId, LocalDate dateMin, LocalDate dateMax, int page, int eventsPerPage);

    ResponseEntity<List<EventResponse>> getEventsByDate(LocalDate dateMin, LocalDate dateMax, int page, int eventsPerPage);

}
