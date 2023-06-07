package dev.dima.transactionservice.controllers;

import dev.dima.transactionservice.api.TransactionApi;
import dev.dima.transactionservice.dtos.requests.TransactionRequest;
import dev.dima.transactionservice.dtos.responses.TransactionResponse;
import dev.dima.transactionservice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionApi {

    private final TransactionService transactionService;

    @Override
    public ResponseEntity<TransactionResponse> createTransaction(UUID userId, TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = transactionService.createTransaction(userId, transactionRequest);
        return ResponseEntity.ok(transactionResponse);
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> getAllTransactionsByUserId(UUID userId) {
        List<TransactionResponse> transactionResponses = transactionService.getAllTransactionsByUserId(userId);
        return ResponseEntity.ok(transactionResponses);
    }
}
