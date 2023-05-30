package dev.dima.betservice.services;

import dev.dima.betservice.dtos.requests.DisciplineRequest;
import dev.dima.betservice.dtos.responses.DisciplineResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface DisciplineService {

    ResponseEntity<DisciplineResponse> createDiscipline(DisciplineRequest disciplineRequest);

    ResponseEntity<DisciplineResponse> getDisciplineById(UUID disciplineId);

    ResponseEntity<Void> deleteDisciplineById(UUID disciplineId);

    ResponseEntity<DisciplineResponse> updateDiscipline(UUID disciplineId, DisciplineRequest disciplineRequest);

    ResponseEntity<List<DisciplineResponse>> getAllDisciplines();
}
