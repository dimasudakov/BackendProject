package dev.dima.transactionservice.exceptions;

import org.springframework.http.HttpStatus;

public class UnexpectedException extends TransactionServiceException{

    public UnexpectedException() {
        super(HttpStatus.CONFLICT, "");
    }
}
