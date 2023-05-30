package dev.dima.betservice.exceptions;

import org.springframework.http.HttpStatus;

public class MultipleBetsOnSameEventException extends BetServiceException{

    public MultipleBetsOnSameEventException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
