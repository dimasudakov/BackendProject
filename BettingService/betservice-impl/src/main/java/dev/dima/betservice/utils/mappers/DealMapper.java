package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.dtos.requests.DealRequest;
import dev.dima.betservice.dtos.responses.DealResponse;
import dev.dima.betservice.models.Bet;
import dev.dima.betservice.models.Deal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {BetMapper.class, IdEntityMapper.class})
public interface DealMapper {

    @Mapping(source = "bets", target = "bets", ignore = true)
    Deal toEntity(DealRequest dealRequest);

    DealResponse toResponse(Deal deal);

    List<Bet> map(List<UUID> value);

    List<DealResponse> toResponseList(List<Deal> deals);
}