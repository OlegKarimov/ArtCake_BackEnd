package de.ait.artcake.validation.constraints;

import de.ait.artcake.validation.validators.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface CorrectDateValidator {

    String message() default "Can't be earlier than today";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
