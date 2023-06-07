package dev.dima.transactionservice.dtos.responses;


import dev.dima.transactionservice.dtos.enums.TransactionType;
import dev.dima.transactionservice.dtos.requests.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TransactionResponse {

    private int transactionAmount;

    private TransactionType transactionType;
}
