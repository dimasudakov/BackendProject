package dev.dima.authservice.exceptions;


import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AuthServiceException {

    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

