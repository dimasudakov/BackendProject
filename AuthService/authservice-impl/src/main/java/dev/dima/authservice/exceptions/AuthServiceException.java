package dev.dima.authservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthServiceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public AuthServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

