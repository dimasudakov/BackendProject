package dev.dima.transactionservice.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class BalanceResponse {

    private final UUID userID;

    private final int balance;
}
