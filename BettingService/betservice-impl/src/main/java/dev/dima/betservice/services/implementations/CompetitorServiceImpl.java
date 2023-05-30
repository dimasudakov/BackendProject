package dev.dima.betservice.services.implementations;

import dev.dima.betservice.dtos.requests.CompetitorRequest;
import dev.dima.betservice.dtos.responses.CompetitorResponse;
import dev.dima.betservice.exceptions.NotFoundException;
import dev.dima.betservice.exceptions.ReferencedEntityException;
import dev.dima.betservice.models.Competitor;
import dev.dima.betservice.repositories.CompetitorRepository;
import dev.dima.betservice.services.CompetitorService;
import dev.dima.betservice.utils.mappers.CompetitorMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CompetitorServiceImpl implements CompetitorService {

    private final CompetitorRepository competitorRepository;

    private final CompetitorMapper competitorMapper;

    @Override
    public ResponseEntity<CompetitorResponse> createCompetitor(@NotNull CompetitorRequest competitorRequest) {
        Competitor competitor = competitorMapper.toEntity(competitorRequest);
        competitor = competitorRepository.save(competitor);
        return ResponseEntity.ok(competitorMapper.toResponse(competitor));
    }

    @Override
    public ResponseEntity<CompetitorResponse> getCompetitorById(@NotNull UUID competitorId) {
        Competitor competitor = competitorRepository.findById(competitorId)
                .orElseThrow(() -> new NotFoundException("Команда или участник не найдены"));
        return ResponseEntity.ok(competitorMapper.toResponse(competitor));
    }

    @Override
    public ResponseEntity<Void> deleteCompetitorById(@NotNull UUID competitorId) {
        if(!competitorRepository.existsById(competitorId)) {
            throw new NotFoundException("Не получилось удалить");
        }
        try {
            competitorRepository.deleteById(competitorId);
        } catch (DataIntegrityViolationException e) {
            throw new ReferencedEntityException("Нельзя удалить так как есть связанные события");
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CompetitorResponse> updateCompetitor(@NotNull UUID competitorId,
                                                               @NotNull CompetitorRequest competitorRequest) {
        if(!competitorRepository.existsById(competitorId)) {
            throw new NotFoundException("Не получилось обновить");
        }

        Competitor competitor = competitorMapper.toEntity(competitorRequest);

        competitor.setId(competitorId);
        competitor = competitorRepository.save(competitor);

        return ResponseEntity.ok(competitorMapper.toResponse(competitor));
    }

    @Override
    public ResponseEntity<List<CompetitorResponse>> getCompetitorsByDisciplineId(@NotNull UUID disciplineId) {

        List<Competitor> competitors = competitorRepository.findAllByDisciplineId(disciplineId);

        return ResponseEntity.ok(competitorMapper.toResponseList(competitors));
    }
}
