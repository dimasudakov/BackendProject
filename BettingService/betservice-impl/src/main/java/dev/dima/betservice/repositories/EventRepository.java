package dev.dima.betservice.repositories;

import dev.dima.betservice.models.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE e.discipline.id = :disciplineId AND e.startDate BETWEEN :dateMin AND :dateMax AND e.active = true")
    Page<Event> findAllByDisciplineIdAndDateBetween(@Param("disciplineId") UUID disciplineId,
                                                    @Param("dateMin") LocalDateTime dateMin,
                                                    @Param("dateMax") LocalDateTime dateMax,
                                                    Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.startDate BETWEEN :dateMin AND :dateMax AND e.active = true")
    Page<Event> findAllByStartDateBetween(@Param("dateMin") LocalDateTime dateMin,
                                          @Param("dateMax") LocalDateTime dateMax,
                                          Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.active = false WHERE e.startDate < :currentDateTime")
    void deactivateEventsByStartDate(@Param("currentDateTime") LocalDateTime currentDateTime);

    @Query("SELECT e FROM Event e WHERE e.active = false AND NOT EXISTS (SELECT b FROM Bet b WHERE b.eventOutcome.event = e)")
    List<Event> findInactiveEventsWithoutBetOutcome();
}
