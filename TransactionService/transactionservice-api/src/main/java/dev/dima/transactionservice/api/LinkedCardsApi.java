package dev.dima.transactionservice.api;


import dev.dima.transactionservice.dtos.requests.CardRequest;
import dev.dima.transactionservice.dtos.responses.CardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transaction/user/linked-cards")
public interface LinkedCardsApi {

    @PostMapping("/create")
    ResponseEntity<CardResponse> createLinkedCard(@RequestBody CardRequest card);

    @GetMapping()
    ResponseEntity<List<CardResponse>> getAllCardsByUser(@AuthenticationPrincipal UUID userId);

    @DeleteMapping("/{cardId}")
    ResponseEntity<Void> deleteCardById(@AuthenticationPrincipal UUID userId,
                                                @PathVariable UUID cardId);
}
