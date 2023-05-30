package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.models.*;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface IdEntityMapper {

    EventOutcome mapIdToEventOutcome(UUID id);


    Deal mapIdToDeal(UUID id);


    Bet mapIdToBet(UUID id);

    OutcomeType mapIdToOutcomeType(UUID id);

    Event mapIdToEvent(UUID id);

    Discipline mapIdToDiscipline(UUID id);

    Competitor mapIdToCompetitor(UUID id);

    User mapIdToUser(UUID id);
}
