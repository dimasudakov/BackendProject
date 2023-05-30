package dev.dima.betservice.repositories;

import dev.dima.betservice.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE e.discipline.id = :disciplineId AND e.active = true")
    Page<Event> findAllByDisciplineId(@Param("disciplineId") UUID disciplineId,
                                      Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.startDate BETWEEN :dateMin AND :dateMax AND e.active = true")
    Page<Event> findAllByStartDateBetween(@Param("dateMin") LocalDateTime dateMin,
                                          @Param("dateMax") LocalDateTime dateMax,
                                          Pageable pageable);
}
