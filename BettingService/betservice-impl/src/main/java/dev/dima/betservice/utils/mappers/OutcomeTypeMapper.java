package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.dtos.requests.OutcomeTypeRequest;
import dev.dima.betservice.dtos.responses.OutcomeTypeResponse;
import dev.dima.betservice.models.OutcomeType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutcomeTypeMapper {

    OutcomeType toEntity(OutcomeTypeRequest outcomeTypeRequest);

    OutcomeTypeResponse toResponse(OutcomeType outcomeType);
}
