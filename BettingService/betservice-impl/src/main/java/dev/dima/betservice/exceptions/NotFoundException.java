package dev.dima.betservice.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BetServiceException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
