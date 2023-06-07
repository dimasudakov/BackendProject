package dev.dima.transactionservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TransactionServiceException extends RuntimeException {

    private final HttpStatus httpStatus;

    public TransactionServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}