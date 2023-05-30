package dev.dima.authservice.dtos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdultValidator.class)
public @interface Adult {
    String message() default "Вы должны быть старше 18";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}