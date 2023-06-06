package dev.dima.betservice.repositories;

import dev.dima.betservice.models.archive.ArchivedDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DealArchiveRepository extends JpaRepository<ArchivedDeal, UUID> {
}
