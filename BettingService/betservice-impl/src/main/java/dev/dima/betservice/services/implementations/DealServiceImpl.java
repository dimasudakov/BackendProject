package dev.dima.betservice.services.implementations;

import dev.dima.betservice.dtos.requests.DealRequest;
import dev.dima.betservice.dtos.responses.DealResponse;
import dev.dima.betservice.exceptions.CoefficientChangedException;
import dev.dima.betservice.exceptions.InsufficientFundsException;
import dev.dima.betservice.exceptions.MultipleBetsOnSameEventException;
import dev.dima.betservice.models.Bet;
import dev.dima.betservice.models.Deal;
import dev.dima.betservice.models.EventOutcome;
import dev.dima.betservice.models.User;
import dev.dima.betservice.repositories.DealRepository;
import dev.dima.betservice.services.DealService;
import dev.dima.betservice.services.UserService;
import dev.dima.betservice.utils.mappers.BetMapper;
import dev.dima.betservice.utils.mappers.DealMapper;
import dev.dima.betservice.utils.mappers.IdEntityMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final UserService userService;

    private final DealMapper dealMapper;
    private final IdEntityMapper idEntityMapper;
    private final BetMapper betMapper;

    @Override
    public ResponseEntity<DealResponse> createDeal(@NotNull DealRequest dealRequest, @NotNull UUID userId) {
        Deal deal = dealMapper.toEntity(dealRequest);
        User user = idEntityMapper.mapIdToUser(userId);

        for(UUID id : dealRequest.getBets()) {
            EventOutcome eventOutcome = idEntityMapper.mapIdToEventOutcome(id);
            Bet bet = betMapper.createBetFromEventOutcome(eventOutcome, deal);
            deal.getBets().add(bet);
        }

        checkDeal(deal, user);

        deal.setUser(idEntityMapper.mapIdToUser(userId));
        user.setBalance(user.getBalance() - deal.getMoney());
        userService.updateUser(user);
        deal = dealRepository.save(deal);
        return ResponseEntity.ok(dealMapper.toResponse(deal));
    }
    private void checkDeal(@NotNull Deal deal, @NotNull User user) {
        if(deal.getMoney() > user.getBalance()) {
            throw new InsufficientFundsException("Недостаточно средств на счете");
        }

        double actualCoefficient = 1.0;
        Set<UUID> uniqueIds = new HashSet<>();
        for(Bet bet : deal.getBets()) {
            actualCoefficient *= bet.getCoefficient();
            if (!uniqueIds.add(bet.getEventOutcome().getEvent().getId())) {
                throw new MultipleBetsOnSameEventException("В экспрессе не должно быть более одной ставки на одно событие");
            }
        }
        if(Math.abs(actualCoefficient - deal.getCoefficient()) >= 0.01) {
            throw new CoefficientChangedException("Коэффициенты изменились");
        }
    }

    @Override
    public void sellDeal(@NotNull UUID dealId, @NotNull UUID userId) {

    }

    @Override
    public ResponseEntity<DealResponse> getDealById(@NotNull UUID dealId, @NotNull UUID userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<DealResponse>> getDealsByUserIdAndStatus(@NotNull UUID userId, Boolean active) {
        return null;
    }

    @Override
    public ResponseEntity<List<DealResponse>> getDealsByUserId(@NotNull UUID userId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<Deal> deals = dealRepository.findAllByUserId(userId, sort);
        return ResponseEntity.ok(dealMapper.toResponseList(deals));
    }
}
