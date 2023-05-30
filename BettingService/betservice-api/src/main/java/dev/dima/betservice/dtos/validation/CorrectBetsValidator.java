package dev.dima.betservice.dtos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.UUID;

public class CorrectBetsValidator implements ConstraintValidator<CorrectBets, List<UUID>> {
    @Override
    public boolean isValid(List<UUID> bets, ConstraintValidatorContext context) {
        return (bets != null && bets.size() > 0);
    }
}
