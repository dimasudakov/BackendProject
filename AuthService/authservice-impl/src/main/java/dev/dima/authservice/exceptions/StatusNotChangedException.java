package dev.dima.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class StatusNotChangedException extends AuthServiceException{

    public StatusNotChangedException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
