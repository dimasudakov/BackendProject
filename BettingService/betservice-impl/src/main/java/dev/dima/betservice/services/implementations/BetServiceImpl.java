package dev.dima.betservice.services.implementations;

import dev.dima.betservice.dtos.requests.BetRequest;
import dev.dima.betservice.dtos.responses.BetResponse;
import dev.dima.betservice.services.BetService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class BetServiceImpl implements BetService {
    @Override
    public ResponseEntity<List<BetResponse>> getAllBetsByEventId(UUID eventId) {
        return null;
    }

    @Override
    public void blockBet(UUID betId) {

    }

    @Override
    public void unblockBet(UUID betId) {

    }

    @Override
    public ResponseEntity<BetRequest> createBet(BetRequest bet) {
        return null;
    }

    @Override
    public ResponseEntity<BetResponse> getBetById(UUID betId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteBet(UUID betId) {
        return null;
    }

    @Override
    public ResponseEntity<BetRequest> updateBet(UUID betId, BetRequest bet) {
        return null;
    }
}
