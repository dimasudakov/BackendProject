package dev.dima.betservice.exceptions;

import org.springframework.http.HttpStatus;

public class UnexpectedException extends BetServiceException {

    public UnexpectedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Непредвиденная ошибка");
    }
}