package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.dtos.requests.CompetitorRequest;
import dev.dima.betservice.dtos.responses.CompetitorResponse;
import dev.dima.betservice.models.Competitor;
import dev.dima.betservice.models.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {IdEntityMapper.class})
public interface CompetitorMapper {

    @Mapping(source = "disciplineId", target = "discipline")
    Competitor toEntity(CompetitorRequest competitorRequest);

    @Mapping(source = "discipline", target = "disciplineId")
    CompetitorResponse toResponse(Competitor competitor);

    List<CompetitorResponse> toResponseList(List<Competitor> competitors);
    default UUID map(Discipline discipline) {
        return discipline.getId();
    }
}
