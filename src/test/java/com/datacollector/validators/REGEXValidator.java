package com.datacollector.validators;

import com.datacollector.validators.anotations.ValidRegex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class REGEXValidator implements ConstraintValidator<ValidRegex, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Pattern.compile(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
