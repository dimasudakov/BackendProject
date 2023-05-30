package dev.dima.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class BetServiceNotAvailableException extends AuthServiceException {

    public BetServiceNotAvailableException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
