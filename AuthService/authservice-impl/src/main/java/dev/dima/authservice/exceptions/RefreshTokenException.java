package dev.dima.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class RefreshTokenException extends AuthServiceException {

    public RefreshTokenException(String token, String message) {
        super(HttpStatus.UNAUTHORIZED, String.format("Failed for [%s]: %s", token, message));
    }
}
