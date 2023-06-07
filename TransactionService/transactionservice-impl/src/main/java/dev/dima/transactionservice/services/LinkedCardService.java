package dev.dima.transactionservice.services;


import dev.dima.transactionservice.dtos.requests.CardRequest;
import dev.dima.transactionservice.dtos.responses.CardResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface LinkedCardService {
    CardResponse createLinkedCard(CardRequest card);

    List<CardResponse> getCardsByUser(UUID userId);

    void deleteLinkedCard(UUID userId, UUID cardId);
}
