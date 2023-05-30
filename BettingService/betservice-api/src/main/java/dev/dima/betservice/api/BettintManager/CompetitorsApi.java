package dev.dima.betservice.api.BettintManager;


import dev.dima.betservice.dtos.requests.CompetitorRequest;
import dev.dima.betservice.dtos.responses.CompetitorResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/betting/manager")
public interface CompetitorsApi {

    @PostMapping("/competitors")
    ResponseEntity<CompetitorResponse> createCompetitor(@Valid @RequestBody CompetitorRequest competitorRequest);

    @GetMapping("/competitors/{competitorId}")
    ResponseEntity<CompetitorResponse> getCompetitor(@PathVariable UUID competitorId);

    @DeleteMapping("/competitors/{competitorId}")
    ResponseEntity<Void> deleteCompetitor(@PathVariable UUID competitorId);

    @PutMapping("/competitors/{competitorId}")
    ResponseEntity<CompetitorResponse> updateCompetitor(@PathVariable UUID competitorId,
                                                        @Valid @RequestBody CompetitorRequest competitorRequest);

    @GetMapping("/competitors")
    ResponseEntity<List<CompetitorResponse>> getCompetitorsByDisciplineId(@RequestParam UUID disciplineId);
}
