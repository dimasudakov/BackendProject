package dev.dima.betservice.controllers.BettingManager;

import dev.dima.betservice.api.BettintManager.DisciplinesApi;
import dev.dima.betservice.dtos.requests.DisciplineRequest;
import dev.dima.betservice.dtos.responses.DisciplineResponse;
import dev.dima.betservice.services.DisciplineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
public class DisciplinesController implements DisciplinesApi {

    private final DisciplineService disciplineService;

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<DisciplineResponse> createDiscipline(@Valid DisciplineRequest disciplineRequest) {
        return disciplineService.createDiscipline(disciplineRequest);
    }

    @Override
    public ResponseEntity<DisciplineResponse> getDiscipline(UUID disciplineId) {
        return disciplineService.getDisciplineById(disciplineId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<Void> deleteDiscipline(UUID disciplineId) {
        return disciplineService.deleteDisciplineById(disciplineId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<DisciplineResponse> updateCompetitor(UUID disciplineId, @Valid DisciplineRequest disciplineRequest) {
        return disciplineService.updateDiscipline(disciplineId, disciplineRequest);
    }

    @Override
    public ResponseEntity<List<DisciplineResponse>> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

}
