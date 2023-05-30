package dev.dima.betservice.services.implementations;

import dev.dima.betservice.dtos.enums.ResultType;
import dev.dima.betservice.dtos.requests.EventOutcomeRequest;
import dev.dima.betservice.dtos.requests.EventOutcomeResultRequest;
import dev.dima.betservice.dtos.responses.EventOutcomeResponse;
import dev.dima.betservice.exceptions.DuplicateEventOutcomeException;
import dev.dima.betservice.exceptions.NotFoundException;
import dev.dima.betservice.exceptions.ReferencedEntityException;
import dev.dima.betservice.exceptions.UnexpectedException;
import dev.dima.betservice.models.*;
import dev.dima.betservice.repositories.DealRepository;
import dev.dima.betservice.repositories.EventOutcomeRepository;
import dev.dima.betservice.services.EventOutcomeService;
import dev.dima.betservice.utils.mappers.EventOutcomeMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EventOutcomeServiceImpl implements EventOutcomeService {

    private final EventOutcomeRepository eventOutcomeRepository;

    private final DealRepository dealRepository;

    private final EventOutcomeMapper eventOutcomeMapper;

    @Override
    public ResponseEntity<EventOutcomeResponse> createEventOutcome(@NotNull EventOutcomeRequest eventOutcomeRequest) {
        EventOutcome eventOutcome = eventOutcomeMapper.toEntity(eventOutcomeRequest);
        if(eventOutcome.getOutcomeType() == null) {
            OutcomeType outcomeType = new OutcomeType();
            outcomeType.setType(eventOutcomeRequest.getOutcomeName());
            eventOutcome.setOutcomeType(outcomeType);
        } else if(eventOutcomeRepository.existsByEventAndOutcomeType(
                eventOutcome.getEvent(),
                eventOutcome.getOutcomeType()
        )) {
            throw new DuplicateEventOutcomeException("Такой исход для такого события уже существует");
        }
        eventOutcome = eventOutcomeRepository.save(eventOutcome);
        return ResponseEntity.ok(eventOutcomeMapper.toResponse(eventOutcome));
    }

    @Override
    public ResponseEntity<List<EventOutcomeResponse>> getEventOutcomeByEventId(@NotNull UUID eventId) {
        List<EventOutcome> eventOutcome = eventOutcomeRepository.findAllByEventId(eventId);
        return ResponseEntity.ok(eventOutcomeMapper.ListToResponse(eventOutcome));
    }

    @Override
    public ResponseEntity<Void> deleteOutcome(@NotNull UUID eventOutcomeId) {
        if(!eventOutcomeRepository.existsById(eventOutcomeId)) {
            throw new NotFoundException("Не получилось удалить");
        }
        try {
            eventOutcomeRepository.deleteById(eventOutcomeId);
        } catch (DataIntegrityViolationException e) {
            throw new ReferencedEntityException("Нельзя удалить так как есть связанные события");
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<EventOutcomeResponse> updateOutcome(@NotNull UUID eventOutcomeId, @NotNull EventOutcomeRequest eventOutcomeRequest) {
        if(!eventOutcomeRepository.existsById(eventOutcomeId)) {
            throw new NotFoundException("Не получилось обновить");
        }

        EventOutcome eventOutcome = eventOutcomeMapper.toEntity(eventOutcomeRequest);

        eventOutcome.setId(eventOutcomeId);
        eventOutcome = eventOutcomeRepository.save(eventOutcome);

        return ResponseEntity.ok(eventOutcomeMapper.toResponse(eventOutcome));
    }

    @Override
    public ResponseEntity<EventOutcomeResponse> getEventOutcomeById(@NotNull UUID eventOutcomeId) {
        EventOutcome eventOutcome = eventOutcomeRepository.findById(eventOutcomeId)
                .orElseThrow(() -> new NotFoundException("Не удалось найти исход"));
        return ResponseEntity.ok(eventOutcomeMapper.toResponse(eventOutcome));
    }

    @Override
    public ResponseEntity<EventOutcomeResponse> completeOutcome(@NotNull UUID eventOutcomeId, @NotNull EventOutcomeResultRequest result) {
        EventOutcome eventOutcome = eventOutcomeRepository.findById(eventOutcomeId)
                .orElseThrow(() -> new NotFoundException("Не удалось найти исход"));
        if(eventOutcome.getStatus() != null) {
            throw new UnexpectedException();
        }

        eventOutcome.setStatus(result.getResult());
        eventOutcomeRepository.save(eventOutcome);

        List<Deal> deals = dealRepository.findDealsByOutcomeId(eventOutcomeId);

        for(Deal deal : deals) {
            int status = 1;

            for(Bet bet : deal.getBets()) {
                EventOutcome outcome = bet.getEventOutcome();
                if(outcome.getStatus() == ResultType.LOSE) {
                    status = -1;
                    break;
                }
                if(outcome.getStatus() == null) {
                    status = 0;
                }
            }

            if(status == 1) {
                deal.setStatus(ResultType.WIN);
                User user = deal.getUser();
                int profit = (int) (deal.getMoney() * deal.getCoefficient());
                user.setBalance(user.getBalance() + profit);
                dealRepository.save(deal);
            }
            if(status == -1) {
                deal.setStatus(ResultType.LOSE);
                dealRepository.save(deal);
            }

        }

        return ResponseEntity.ok(eventOutcomeMapper.toResponse(eventOutcome));
    }
}
