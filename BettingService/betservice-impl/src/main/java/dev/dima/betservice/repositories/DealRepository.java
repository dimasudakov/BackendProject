package dev.dima.betservice.repositories;

import dev.dima.betservice.models.Deal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface DealRepository extends JpaRepository<Deal, UUID> {

    //@Query("SELECT d FROM Deal d JOIN d.bets b WHERE b.eventOutcome.id = :eventOutcomeId")
    @Query("SELECT d FROM Deal d WHERE d.id IN (SELECT b.deal.id FROM Bet b WHERE b.eventOutcome.id = :eventOutcomeId)")
    List<Deal> findDealsByOutcomeId(@Param("eventOutcomeId") UUID eventOutcomeId);

    List<Deal> findAllByUserId(UUID userId, Sort sort);
}
