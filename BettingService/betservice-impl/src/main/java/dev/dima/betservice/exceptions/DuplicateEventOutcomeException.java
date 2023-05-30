package dev.dima.betservice.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateEventOutcomeException extends BetServiceException {
    public DuplicateEventOutcomeException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
