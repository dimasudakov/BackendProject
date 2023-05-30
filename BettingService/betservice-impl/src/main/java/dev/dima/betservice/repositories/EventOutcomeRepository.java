package dev.dima.betservice.repositories;

import dev.dima.betservice.models.Event;
import dev.dima.betservice.models.EventOutcome;
import dev.dima.betservice.models.OutcomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventOutcomeRepository extends JpaRepository<EventOutcome, UUID> {
    @Query("SELECT eo FROM EventOutcome eo WHERE eo.event.id = :eventId AND eo.status IS NULL")
    List<EventOutcome> findAllByEventId(@Param("eventId") UUID eventId);


    boolean existsByEventAndOutcomeType(Event event, OutcomeType outcomeType);
}
