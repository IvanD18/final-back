package ru.rosbank.javaschool.crudapi.validator;

import ru.rosbank.javaschool.crudapi.constraint.NameValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectNameValidator implements ConstraintValidator<NameValidator, String> {
   private String[] list;

//   public void initialize(LoginValidator constraint) {
//      list = constraint.value();
//   }

   public boolean isValid(String value, ConstraintValidatorContext context) {
       boolean a=true;
        for(int i=0;i!=10;i++){
            if(value.contains(String.valueOf(i))){
                    a=false;
                    return a;
                }
            a=true;
        }

       return a;
}
}
