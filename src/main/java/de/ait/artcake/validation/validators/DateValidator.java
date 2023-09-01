package de.ait.artcake.validation.validators;

import de.ait.artcake.validation.constraints.CorrectDateValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class DateValidator implements ConstraintValidator<CorrectDateValidator, String> {

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {

        try {
            LocalDate deadline = LocalDate.parse(date);
            return !deadline.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

