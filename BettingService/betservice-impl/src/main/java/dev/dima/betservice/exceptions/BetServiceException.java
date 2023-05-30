package dev.dima.betservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BetServiceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public BetServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
