package dev.dima.betservice.services.implementations;

import dev.dima.betservice.dtos.requests.OutcomeTypeRequest;
import dev.dima.betservice.dtos.responses.OutcomeTypeResponse;
import dev.dima.betservice.exceptions.NotFoundException;
import dev.dima.betservice.exceptions.ReferencedEntityException;
import dev.dima.betservice.models.OutcomeType;
import dev.dima.betservice.repositories.OutcomeTypeRepository;
import dev.dima.betservice.services.OutcomeTypeService;
import dev.dima.betservice.utils.mappers.OutcomeTypeMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutcomeTypeServiceImpl implements OutcomeTypeService {

    private final OutcomeTypeRepository outcomeTypeRepository;

    private final OutcomeTypeMapper outcomeTypeMapper;


    @Override
    public ResponseEntity<OutcomeTypeResponse> createOutcomeType(@NotNull OutcomeTypeRequest outcomeTypeRequest) {
        OutcomeType outcomeType = outcomeTypeMapper.toEntity(outcomeTypeRequest);
        outcomeType = outcomeTypeRepository.save(outcomeType);
        return ResponseEntity.ok(outcomeTypeMapper.toResponse(outcomeType));
    }

    @Override
    public ResponseEntity<OutcomeTypeResponse> getOutcomeTypeById(@NotNull UUID outcomeTypeId) {
        OutcomeType outcomeType = outcomeTypeRepository.findById(outcomeTypeId)
                .orElseThrow(() -> new NotFoundException("Исход не найден"));
        return ResponseEntity.ok(outcomeTypeMapper.toResponse(outcomeType));
    }

    @Override
    public ResponseEntity<Void> deleteOutcomeType(@NotNull UUID outcomeTypeId) {
        if(!outcomeTypeRepository.existsById(outcomeTypeId)) {
            throw new NotFoundException("Не получилось удалить");
        }
        try {
            outcomeTypeRepository.deleteById(outcomeTypeId);
        } catch (DataIntegrityViolationException e) {
            throw new ReferencedEntityException("Нельзя удалить так как есть связанные исходы");
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<OutcomeTypeResponse> updateOutcomeType(@NotNull UUID outcomeTypeId, @NotNull OutcomeTypeRequest outcomeTypeRequest) {
        if(!outcomeTypeRepository.existsById(outcomeTypeId)) {
            throw new NotFoundException("Не получилось обновить");
        }

        OutcomeType outcomeType = outcomeTypeMapper.toEntity(outcomeTypeRequest);

        outcomeType.setId(outcomeTypeId);
        outcomeType = outcomeTypeRepository.save(outcomeType);

        return ResponseEntity.ok(outcomeTypeMapper.toResponse(outcomeType));
    }
}
