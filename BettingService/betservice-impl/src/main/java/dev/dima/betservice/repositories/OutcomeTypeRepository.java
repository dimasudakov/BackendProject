package dev.dima.betservice.repositories;


import dev.dima.betservice.models.OutcomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutcomeTypeRepository extends JpaRepository<OutcomeType, UUID> {
}
