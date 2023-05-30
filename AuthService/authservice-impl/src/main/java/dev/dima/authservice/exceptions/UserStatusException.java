package dev.dima.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class UserStatusException extends AuthServiceException{
    public UserStatusException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
