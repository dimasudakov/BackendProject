package dev.dima.betservice.dtos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectBetsValidator.class)
public @interface CorrectBets {
    String message() default "Должна быть хоть одна ставка в сделке";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}