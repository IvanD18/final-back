package ru.rosbank.javaschool.crudapi.constraint;

import ru.rosbank.javaschool.crudapi.validator.CorrectNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CorrectNameValidator.class)
public @interface NameValidator {
    String message() default "Имя не может содержать чисел";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String[] value() default {};
}
