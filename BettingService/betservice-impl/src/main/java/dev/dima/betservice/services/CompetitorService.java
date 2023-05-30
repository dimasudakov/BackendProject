package dev.dima.betservice.services;


import dev.dima.betservice.dtos.requests.CompetitorRequest;
import dev.dima.betservice.dtos.responses.CompetitorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CompetitorService {

    ResponseEntity<CompetitorResponse> createCompetitor(CompetitorRequest competitorRequest);

    ResponseEntity<CompetitorResponse> getCompetitorById(UUID competitorId);

    ResponseEntity<Void> deleteCompetitorById(UUID competitorId);

    ResponseEntity<CompetitorResponse> updateCompetitor(UUID competitorId, CompetitorRequest competitorRequest);

    ResponseEntity<List<CompetitorResponse>> getCompetitorsByDisciplineId(UUID disciplineId);
}
