package dev.dima.betservice.exceptions;

import org.springframework.http.HttpStatus;

public class DealCantBeSoldException extends BetServiceException {

    public DealCantBeSoldException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
