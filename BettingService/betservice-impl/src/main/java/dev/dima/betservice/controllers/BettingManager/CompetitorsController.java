package dev.dima.betservice.controllers.BettingManager;

import dev.dima.betservice.api.BettintManager.CompetitorsApi;
import dev.dima.betservice.dtos.requests.CompetitorRequest;
import dev.dima.betservice.dtos.responses.CompetitorResponse;
import dev.dima.betservice.services.CompetitorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
public class CompetitorsController implements CompetitorsApi {

    private final CompetitorService competitorService;

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<CompetitorResponse> createCompetitor(@Valid CompetitorRequest competitorRequest) {
        return competitorService.createCompetitor(competitorRequest);
    }

    @Override
    public ResponseEntity<CompetitorResponse> getCompetitor(UUID competitorId) {
        return competitorService.getCompetitorById(competitorId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<Void> deleteCompetitor(UUID competitorId) {
        return competitorService.deleteCompetitorById(competitorId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<CompetitorResponse> updateCompetitor(UUID competitorId, @Valid CompetitorRequest competitorRequest) {
        return competitorService.updateCompetitor(competitorId, competitorRequest);
    }

    @Override
    public ResponseEntity<List<CompetitorResponse>> getCompetitorsByDisciplineId(UUID disciplineId) {
        return competitorService.getCompetitorsByDisciplineId(disciplineId);
    }
}
