package dev.dima.betservice.services;

import dev.dima.betservice.dtos.requests.BetRequest;
import dev.dima.betservice.dtos.responses.BetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public interface BetService {
    ResponseEntity<List<BetResponse>> getAllBetsByEventId(UUID eventId);

    void blockBet(UUID betId);

    void unblockBet(UUID betId);

    ResponseEntity<BetRequest> createBet(BetRequest bet);

    ResponseEntity<BetResponse> getBetById(UUID betId);

    ResponseEntity<Void> deleteBet(UUID betId);

    ResponseEntity<BetRequest> updateBet(UUID betId, BetRequest bet);
}
