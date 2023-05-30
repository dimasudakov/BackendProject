package dev.dima.authservice.controllers.handlers;


import dev.dima.authservice.exceptions.AuthServiceException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {


    @ExceptionHandler(AuthServiceException.class)
    public ResponseEntity<ExceptionMessage> handleUserRegistrationException(AuthServiceException ex) {
        log.error("AuthServiceException occurred: {}", ex.getMessage(), ex);

        return ResponseEntity.status(ex.getHttpStatus())
                .body(ExceptionMessage.builder()
                        .message(ex.getMessage())
                        .exceptionName(ex.getClass().getSimpleName())
                        .build());
    }


    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException occurred", ex);

        final List<Violation> violations = ex.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException occurred");

        final List<Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

}

