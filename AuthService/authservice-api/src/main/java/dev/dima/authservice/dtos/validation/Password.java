package dev.dima.authservice.dtos.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(
        regexp = "^[a-zA-Z0-9]{4,20}$",
        message = "Password must be between 4 and 20 characters long and contain only letters and numbers"
)
public @interface Password {

    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}