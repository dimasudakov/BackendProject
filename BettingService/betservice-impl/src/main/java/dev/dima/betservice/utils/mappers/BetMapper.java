package dev.dima.betservice.utils.mappers;

import dev.dima.betservice.dtos.requests.BetRequest;
import dev.dima.betservice.dtos.responses.BetResponse;
import dev.dima.betservice.models.Bet;
import dev.dima.betservice.models.Deal;
import dev.dima.betservice.models.EventOutcome;
import dev.dima.betservice.models.OutcomeType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {IdEntityMapper.class, EventOutcomeMapper.class})
public interface BetMapper {

    @Mapping(source = "eventOutcomeId", target = "eventOutcome")
    @Mapping(source = "dealId", target = "deal")
    Bet toEntity(BetRequest betRequest);

    @Mapping(source = "deal", target = "dealId")
    BetResponse toResponse(Bet bet);

    default UUID map(EventOutcome eventOutcome) {
        return eventOutcome.getId();
    }

    default UUID map(Deal deal) {
        return deal.getId();
    }

    default Bet createBetFromEventOutcome(EventOutcome eventOutcome, Deal deal) {
        Bet bet = new Bet();
        bet.setEventOutcome(eventOutcome);
        bet.setDeal(deal);
        bet.setCoefficient(eventOutcome.getCoefficient());
        return bet;
    }

    default String map(OutcomeType outcomeType) {
        return outcomeType.getType();
    }
}
