package dev.dima.transactionservice.services;


import dev.dima.transactionservice.dtos.requests.TransactionRequest;
import dev.dima.transactionservice.dtos.responses.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TransactionService {
    TransactionResponse createTransaction(UUID userId, TransactionRequest transactionRequest);

    List<TransactionResponse> getAllTransactionsByUserId(UUID userId);
}
