package dev.dima.betservice.exceptions;

import org.springframework.http.HttpStatus;

public class CoefficientChangedException extends BetServiceException{

    public CoefficientChangedException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
