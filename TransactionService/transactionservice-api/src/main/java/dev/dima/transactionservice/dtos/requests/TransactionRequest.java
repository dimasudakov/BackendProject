package dev.dima.transactionservice.dtos.requests;

import dev.dima.transactionservice.dtos.enums.TransactionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@Getter
@RequiredArgsConstructor
public class TransactionRequest {

    private UserRequest user;

    private int transactionAmount;

    private TransactionType transactionType;
}
