package dev.dima.transactionservice.services.implementations;

import dev.dima.transactionservice.dtos.requests.TransactionRequest;
import dev.dima.transactionservice.dtos.responses.TransactionResponse;
import dev.dima.transactionservice.exceptions.NotFoundException;
import dev.dima.transactionservice.models.Transaction;
import dev.dima.transactionservice.models.User;
import dev.dima.transactionservice.repositories.TransactionRepository;
import dev.dima.transactionservice.repositories.UserRepository;
import dev.dima.transactionservice.services.TransactionService;
import dev.dima.transactionservice.utils.mappers.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponse createTransaction(UUID userId, TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toResponse(transaction);
    }

    @Override
    public List<TransactionResponse> getAllTransactionsByUserId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Не удалось найти"));

        return transactionMapper.toListResponse(user.getTransactions());
    }
}
