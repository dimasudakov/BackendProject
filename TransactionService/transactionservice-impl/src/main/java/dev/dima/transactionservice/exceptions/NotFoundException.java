package dev.dima.transactionservice.exceptions;


import org.springframework.http.HttpStatus;

public class NotFoundException extends TransactionServiceException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
