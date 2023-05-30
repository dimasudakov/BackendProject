package dev.dima.betservice.exceptions;

import org.springframework.http.HttpStatus;


// Недостаточно средств
public class InsufficientFundsException extends BetServiceException {


    public InsufficientFundsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
