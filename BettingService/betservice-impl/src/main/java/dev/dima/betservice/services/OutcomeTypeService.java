package dev.dima.betservice.services;


import dev.dima.betservice.dtos.requests.OutcomeTypeRequest;
import dev.dima.betservice.dtos.responses.OutcomeTypeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface OutcomeTypeService {

    ResponseEntity<OutcomeTypeResponse> createOutcomeType(OutcomeTypeRequest outcomeTypeRequest);

    ResponseEntity<OutcomeTypeResponse> getOutcomeTypeById(UUID outcomeTypeId);

    ResponseEntity<Void> deleteOutcomeType(UUID outcomeTypeId);

    ResponseEntity<OutcomeTypeResponse> updateOutcomeType(UUID outcomeTypeId, OutcomeTypeRequest outcomeTypeRequest);
}
