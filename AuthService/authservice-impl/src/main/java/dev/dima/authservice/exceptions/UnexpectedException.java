package dev.dima.authservice.exceptions;

import org.springframework.http.HttpStatus;

public class UnexpectedException extends AuthServiceException {

    public UnexpectedException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Непредвиденная ошибка");
    }
}