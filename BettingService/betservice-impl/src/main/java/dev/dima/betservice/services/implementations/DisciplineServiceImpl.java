package dev.dima.betservice.services.implementations;

import dev.dima.betservice.dtos.requests.DisciplineRequest;
import dev.dima.betservice.dtos.responses.DisciplineResponse;
import dev.dima.betservice.exceptions.NotFoundException;
import dev.dima.betservice.exceptions.ReferencedEntityException;
import dev.dima.betservice.models.Discipline;
import dev.dima.betservice.repositories.DisciplineRepository;
import dev.dima.betservice.services.DisciplineService;
import dev.dima.betservice.utils.mappers.DisciplineMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;

    private final DisciplineMapper disciplineMapper;

    @Override
    public ResponseEntity<DisciplineResponse> createDiscipline(@NotNull DisciplineRequest disciplineRequest) {
        Discipline discipline = disciplineMapper.toEntity(disciplineRequest);
        discipline = disciplineRepository.save(discipline);
        return ResponseEntity.ok(disciplineMapper.toResponse(discipline));
    }

    @Override
    public ResponseEntity<DisciplineResponse> getDisciplineById(@NotNull UUID disciplineId) {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new NotFoundException("Дисциплина не найдена"));
        return ResponseEntity.ok(disciplineMapper.toResponse(discipline));
    }

    @Override
    public ResponseEntity<Void> deleteDisciplineById(@NotNull UUID disciplineId) {
        if(!disciplineRepository.existsById(disciplineId)) {
            throw new NotFoundException("Не получилось удалить");
        }
        try {
            disciplineRepository.deleteById(disciplineId);
        } catch (DataIntegrityViolationException e) {
            throw new ReferencedEntityException("Нельзя удалить так как есть связанные события");
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<DisciplineResponse> updateDiscipline(UUID disciplineId, @NotNull DisciplineRequest disciplineRequest) {
        if(!disciplineRepository.existsById(disciplineId)) {
            throw new NotFoundException("Не получилось обновить");
        }

        Discipline discipline = disciplineMapper.toEntity(disciplineRequest);

        discipline.setId(disciplineId);
        discipline = disciplineRepository.save(discipline);

        return ResponseEntity.ok(disciplineMapper.toResponse(discipline));
    }

    @Override
    public ResponseEntity<List<DisciplineResponse>> getAllDisciplines() {
        List<Discipline> disciplines = disciplineRepository.findAll();
        return ResponseEntity.ok(disciplineMapper.listToResponse(disciplines));
    }
}
