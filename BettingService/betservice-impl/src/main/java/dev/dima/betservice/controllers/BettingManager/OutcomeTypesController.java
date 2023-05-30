package dev.dima.betservice.controllers.BettingManager;

import dev.dima.betservice.api.BettintManager.OutcomeTypesApi;
import dev.dima.betservice.dtos.requests.OutcomeTypeRequest;
import dev.dima.betservice.dtos.responses.OutcomeTypeResponse;
import dev.dima.betservice.services.OutcomeTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RequiredArgsConstructor
@RestController
public class OutcomeTypesController implements OutcomeTypesApi {

    private final OutcomeTypeService outcomeTypeService;

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<OutcomeTypeResponse> createOutcomeType(@Valid OutcomeTypeRequest outcomeTypeRequest) {
        return outcomeTypeService.createOutcomeType(outcomeTypeRequest);
    }

    @Override
    public ResponseEntity<OutcomeTypeResponse> getOutcomeTypeById(UUID outcomeTypeId) {
        return outcomeTypeService.getOutcomeTypeById(outcomeTypeId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<Void> deleteOutcomeType(UUID outcomeTypeId) {
        return outcomeTypeService.deleteOutcomeType(outcomeTypeId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_BETTING_MANAGER')")
    public ResponseEntity<OutcomeTypeResponse> updateOutcomeType(UUID outcomeTypeId, @Valid OutcomeTypeRequest outcomeTypeRequest) {
        return outcomeTypeService.updateOutcomeType(outcomeTypeId, outcomeTypeRequest);
    }
}
