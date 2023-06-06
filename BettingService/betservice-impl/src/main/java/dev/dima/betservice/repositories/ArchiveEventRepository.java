package dev.dima.betservice.repositories;


import dev.dima.betservice.models.archive.ArchivedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArchiveEventRepository extends JpaRepository<ArchivedEvent, UUID> {
}
