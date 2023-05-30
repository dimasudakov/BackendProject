package dev.dima.betservice.services.implementations;

import dev.dima.betservice.dtos.requests.EventRequest;
import dev.dima.betservice.dtos.requests.EventRequest2;
import dev.dima.betservice.dtos.responses.EventResponse;
import dev.dima.betservice.exceptions.NotFoundException;
import dev.dima.betservice.exceptions.ReferencedEntityException;
import dev.dima.betservice.models.Competitor;
import dev.dima.betservice.models.Discipline;
import dev.dima.betservice.models.Event;
import dev.dima.betservice.repositories.CompetitorRepository;
import dev.dima.betservice.repositories.DisciplineRepository;
import dev.dima.betservice.repositories.EventRepository;
import dev.dima.betservice.services.EventService;
import dev.dima.betservice.utils.mappers.EventMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventMapper eventMapper;

    private final EventRepository eventRepository;

    private final DisciplineRepository disciplineRepository;

    private final CompetitorRepository competitorRepository;

    @Override
    public void blockEvent(@NotNull UUID eventId) {

    }

    @Override
    public void unblockEvent(@NotNull UUID eventId) {

    }

    @Override
    public ResponseEntity<EventResponse> createEvent(@NotNull EventRequest eventRequest) {
        Event event = eventMapper.toEntity(eventRequest);
        event = eventRepository.save(event);
        return ResponseEntity.ok(eventMapper.toResponse(event));
    }

    @Override
    public ResponseEntity<EventResponse> createEvent(@NotNull EventRequest2 eventRequest) {
        Event event = eventMapper.toEntity(eventRequest);

        if(event.getDiscipline() == null) {
            Discipline discipline = new Discipline();
            discipline.setName(eventRequest.getDisciplineName());
            disciplineRepository.save(discipline);
            event.setDiscipline(discipline);
        }
        if(event.getCompetitor1() == null) {
            Competitor competitor = new Competitor();
            competitor.setName(eventRequest.getCompetitor1Name());
            competitor.setDiscipline(event.getDiscipline());
            event.setCompetitor1(competitor);
        }
        if(event.getCompetitor2() == null) {
            Competitor competitor = new Competitor();
            competitor.setName(eventRequest.getCompetitor2Name());
            competitor.setDiscipline(event.getDiscipline());
            event.setCompetitor2(competitor);
        }
        event = eventRepository.save(event);
        return ResponseEntity.ok(eventMapper.toResponse(event));
    }

    @Override
    public ResponseEntity<EventResponse> getEventById(@NotNull UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие не найдено"));
        return ResponseEntity.ok(eventMapper.toResponse(event));
    }


    @Override
    public ResponseEntity<Void> deleteEventById(@NotNull UUID eventId) {
        if(!eventRepository.existsById(eventId)) {
            throw new NotFoundException("Не получилось удалить");
        }
        try {
            eventRepository.deleteById(eventId);
        } catch (DataIntegrityViolationException e) {
            throw new ReferencedEntityException("Нельзя удалить так как есть связанные события");
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<EventResponse> updateEvent(@NotNull UUID eventId, @NotNull EventRequest eventRequest) {
        if(!eventRepository.existsById(eventId)) {
            throw new NotFoundException("Не получилось обновить");
        }

        Event event = eventMapper.toEntity(eventRequest);

        event.setId(eventId);
        event = eventRepository.save(event);

        return ResponseEntity.ok(eventMapper.toResponse(event));
    }

    @Override
    public ResponseEntity<List<EventResponse>> getEventsByDisciplineId(@NotNull UUID disciplineId, int page, int eventsPerPage) {
        Pageable pageable = PageRequest.of(page - 1, eventsPerPage, Sort.by("startDate").ascending());

        Page<Event> events = eventRepository.findAllByDisciplineId(disciplineId, pageable);

        List<EventResponse> eventsResponse = events.getContent().stream()
                .map(eventMapper::toResponse)
                .toList();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Pages", String.valueOf(events.getTotalPages()));
        return ResponseEntity.ok().headers(responseHeaders).body(eventsResponse);
    }

    @Override
    public ResponseEntity<List<EventResponse>> getEventsByDate(@NotNull LocalDate dateMin,
                                                               @NotNull LocalDate dateMax,
                                                               int page,
                                                               int eventsPerPage) {
        LocalDateTime dateTimeMin = dateMin.atStartOfDay();
        LocalDateTime dateTimeMax = dateMax.atTime(LocalTime.MAX);

        Pageable pageable = PageRequest.of(page - 1, eventsPerPage, Sort.by("startDate").ascending());

        Page<Event> events = eventRepository.findAllByStartDateBetween(dateTimeMin, dateTimeMax, pageable);
        List<EventResponse> eventsResponse = events.getContent().stream()
                .map(eventMapper::toResponse)
                .toList();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Pages", String.valueOf(events.getTotalPages()));
        return ResponseEntity.ok().headers(responseHeaders).body(eventsResponse);
    }

}
