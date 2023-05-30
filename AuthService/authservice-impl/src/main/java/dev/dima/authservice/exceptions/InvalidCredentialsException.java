package dev.dima.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends AuthServiceException{

    public InvalidCredentialsException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
