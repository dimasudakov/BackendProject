package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.dtos.requests.DisciplineRequest;
import dev.dima.betservice.dtos.responses.DisciplineResponse;
import dev.dima.betservice.models.Discipline;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {

    Discipline toEntity(DisciplineRequest disciplineRequest);

    DisciplineResponse toResponse(Discipline discipline);

    List<DisciplineResponse> listToResponse(List<Discipline> disciplines);
}
