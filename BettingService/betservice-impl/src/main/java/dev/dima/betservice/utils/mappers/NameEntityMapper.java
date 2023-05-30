package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.models.Competitor;
import dev.dima.betservice.models.Discipline;
import dev.dima.betservice.models.OutcomeType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface NameEntityMapper {

    OutcomeType mapNameToOutcomeType(String name);

    Competitor mapNameToCompetitor(String value);

    Discipline mapNameToDiscipline(String value);
}
