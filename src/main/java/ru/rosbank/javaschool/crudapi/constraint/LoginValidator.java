package ru.rosbank.javaschool.crudapi.constraint;

import ru.rosbank.javaschool.crudapi.validator.CorrectLoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CorrectLoginValidator.class)
public @interface LoginValidator {
  String message() default "Логин должен состоять из 3-20 символов";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
  String[] value() default {};
}
