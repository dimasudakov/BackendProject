package dev.dima.betservice.exceptions;

import org.springframework.http.HttpStatus;

public class ReferencedEntityException extends BetServiceException {

    public ReferencedEntityException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
