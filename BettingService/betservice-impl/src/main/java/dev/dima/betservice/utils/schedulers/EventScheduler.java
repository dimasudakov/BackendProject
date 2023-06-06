package dev.dima.betservice.utils.schedulers;

import dev.dima.betservice.models.Event;
import dev.dima.betservice.models.archive.ArchivedEvent;
import dev.dima.betservice.repositories.ArchiveEventRepository;
import dev.dima.betservice.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventScheduler {

    private final EventRepository eventRepository;

    private final ArchiveEventRepository archiveEventRepository;


    // Все события которые уже начались блокируются
    @Scheduled(cron = "0 * * * * *")
    public void deactivateEvents() {
        LocalDateTime currentTime = LocalDateTime.now();

        eventRepository.deactivateEventsByStartDate(currentTime);
    }

    // архивация событий которые заблокированы и не учавствуют в экспрессах
    @Scheduled(cron = "0 * * * * *")
    public void archiveEvents() {

        List<Event> events = eventRepository.findInactiveEventsWithoutBetOutcome();
        List<ArchivedEvent> archivedEvents = events.stream()
                .map(ArchivedEvent::new)
                .toList();

        archiveEventRepository.saveAll(archivedEvents);
        eventRepository.deleteAll(events);

    }
}