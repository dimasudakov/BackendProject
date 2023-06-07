package dev.dima.transactionservice.controllers;

import dev.dima.transactionservice.api.LinkedCardsApi;
import dev.dima.transactionservice.dtos.requests.CardRequest;
import dev.dima.transactionservice.dtos.responses.CardResponse;
import dev.dima.transactionservice.models.LinkedCard;
import dev.dima.transactionservice.services.LinkedCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LinkedCardsController implements LinkedCardsApi {

    private final LinkedCardService linkedCardService;

    @Override
    public ResponseEntity<CardResponse> createLinkedCard(CardRequest card) {
        CardResponse cardResponse = linkedCardService.createLinkedCard(card);
        return ResponseEntity.ok(cardResponse);
    }

    @Override
    public ResponseEntity<List<CardResponse>> getAllCardsByUser(UUID userId) {
        List<CardResponse> cardResponses = linkedCardService.getCardsByUser(userId);
        return ResponseEntity.ok(cardResponses);
    }

    @Override
    public ResponseEntity<Void> deleteCardById(UUID userId, UUID cardId) {
        linkedCardService.deleteLinkedCard(userId, cardId);
        return ResponseEntity.noContent().build();
    }
}
