package dev.dima.authservice.dtos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        if (dateOfBirth == null) {
            return false;
        }
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return age >= 18;
    }
}
