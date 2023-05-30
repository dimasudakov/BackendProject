package dev.dima.betservice.repositories;


import dev.dima.betservice.models.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, UUID> {

    @Query("SELECT c FROM Competitor c WHERE c.discipline.id = :disciplineId")
    List<Competitor> findAllByDisciplineId(@Param("disciplineId") UUID disciplineId);
}
