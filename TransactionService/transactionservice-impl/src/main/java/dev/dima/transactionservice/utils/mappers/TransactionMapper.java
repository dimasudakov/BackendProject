package dev.dima.transactionservice.utils.mappers;

import dev.dima.transactionservice.dtos.requests.TransactionRequest;
import dev.dima.transactionservice.dtos.responses.TransactionResponse;
import dev.dima.transactionservice.models.Transaction;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toEntity(TransactionRequest transactionRequest);

    TransactionResponse toResponse(Transaction transaction);

    List<TransactionResponse> toListResponse(List<Transaction> transactions);
}
