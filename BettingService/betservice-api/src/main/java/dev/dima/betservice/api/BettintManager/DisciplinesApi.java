package dev.dima.betservice.api.BettintManager;


import dev.dima.betservice.dtos.requests.DisciplineRequest;
import dev.dima.betservice.dtos.responses.DisciplineResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/betting/manager")
public interface DisciplinesApi {

    @PostMapping("/disciplines")
    ResponseEntity<DisciplineResponse> createDiscipline(@RequestBody @Valid DisciplineRequest disciplineRequest);

    @GetMapping("/disciplines/{disciplineId}")
    ResponseEntity<DisciplineResponse> getDiscipline(@PathVariable UUID disciplineId);

    @DeleteMapping("/disciplines/{disciplineId}")
    ResponseEntity<Void> deleteDiscipline(@PathVariable UUID disciplineId);

    @PutMapping("/disciplines/{disciplineId}")
    ResponseEntity<DisciplineResponse> updateCompetitor(@PathVariable UUID disciplineId,
                                                        @Valid @RequestBody DisciplineRequest disciplineRequest);

    @GetMapping("/disciplines")
    ResponseEntity<List<DisciplineResponse>> getAllDisciplines();

}
