package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.dtos.requests.EventRequest;
import dev.dima.betservice.dtos.requests.EventRequest2;
import dev.dima.betservice.dtos.responses.EventResponse;
import dev.dima.betservice.models.Competitor;
import dev.dima.betservice.models.Discipline;
import dev.dima.betservice.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {IdEntityMapper.class, NameEntityMapper.class})
public interface EventMapper {

    @Mapping(source = "disciplineId", target = "discipline")
    @Mapping(source = "competitor1Id", target = "competitor1")
    @Mapping(source = "competitor2Id", target = "competitor2")
    Event toEntity(EventRequest eventRequest);

    @Mapping(source = "disciplineName", target = "discipline")
    @Mapping(source = "competitor1Name", target = "competitor1")
    @Mapping(source = "competitor2Name", target = "competitor2")
    Event toEntity(EventRequest2 eventRequest2);


    @Mapping(source = "discipline", target = "disciplineId")
    @Mapping(source = "competitor1", target = "competitor1Name")
    @Mapping(source = "competitor2", target = "competitor2Name")
    EventResponse toResponse(Event event);

    List<EventResponse> toResponseList(List<Event> events);

    default UUID map(Discipline discipline) {
        return discipline.getId();
    }

    default String map(Competitor competitor) {
        return competitor.getName();
    }

}
