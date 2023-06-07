package dev.dima.transactionservice.exceptions;

import org.springframework.http.HttpStatus;

public class BalanceException extends TransactionServiceException {

    public BalanceException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
