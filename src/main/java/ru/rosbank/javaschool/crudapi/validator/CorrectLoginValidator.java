package ru.rosbank.javaschool.crudapi.validator;

import ru.rosbank.javaschool.crudapi.constraint.LoginValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectLoginValidator implements ConstraintValidator<LoginValidator, String> {
   private String[] list;

//   public void initialize(LoginValidator constraint) {
//      list = constraint.value();
//   }

   public boolean isValid(String value, ConstraintValidatorContext context) {

         return (value.length() >3 && value.length() < 20 );

   }
}
