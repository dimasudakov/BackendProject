package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.dtos.requests.EventOutcomeRequest;
import dev.dima.betservice.dtos.responses.EventOutcomeResponse;
import dev.dima.betservice.models.Event;
import dev.dima.betservice.models.EventOutcome;
import dev.dima.betservice.models.OutcomeType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {IdEntityMapper.class, NameEntityMapper.class})
public interface EventOutcomeMapper {

    @Mapping(source = "outcomeName", target = "outcomeType")
    @Mapping(source = "eventId", target = "event")
    EventOutcome toEntity(EventOutcomeRequest eventOutcomeRequest);

    @Mapping(source = "event", target = "eventName")
    @Mapping(source = "event", target = "eventId")
    EventOutcomeResponse toResponse(EventOutcome eventOutcome);

    default UUID map(OutcomeType outcomeType) {
        return outcomeType.getId();
    }

    default UUID map(Event event) {
        return event.getId();
    }

    default String mapType(OutcomeType outcomeType) {
        return outcomeType.getType();
    }

    List<EventOutcomeResponse> ListToResponse(List<EventOutcome> eventOutcome);

    default String mapToString(Event event) {
        return (event.getCompetitor1().getName() + " vs " + event.getCompetitor2().getName());
    }
}
