package dev.dima.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AuthServiceException {

    public UserAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

}
