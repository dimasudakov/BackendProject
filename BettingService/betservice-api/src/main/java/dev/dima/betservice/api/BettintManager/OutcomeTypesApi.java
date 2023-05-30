package dev.dima.betservice.api.BettintManager;


import dev.dima.betservice.dtos.requests.OutcomeTypeRequest;
import dev.dima.betservice.dtos.responses.OutcomeTypeResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/betting/manager")
public interface OutcomeTypesApi {

    @PostMapping("/outcome-types")
    ResponseEntity<OutcomeTypeResponse> createOutcomeType(@Valid @RequestBody OutcomeTypeRequest outcomeTypeRequest);

    @GetMapping("/outcome-types/{outcomeTypeId}")
    ResponseEntity<OutcomeTypeResponse> getOutcomeTypeById(@PathVariable UUID outcomeTypeId);

    @DeleteMapping("/outcome-types/{outcomeTypeId}")
    ResponseEntity<Void> deleteOutcomeType(@PathVariable UUID outcomeTypeId);

    @PutMapping("/outcome-types/{outcomeTypeId}")
    ResponseEntity<OutcomeTypeResponse> updateOutcomeType(@PathVariable UUID outcomeTypeId,
                                                          @Valid @RequestBody OutcomeTypeRequest outcomeTypeRequest);

}
