package com.datacollector.validators;

import com.datacollector.validators.anotations.VariableFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class VariableFormatValidator implements ConstraintValidator<VariableFormat, String> {
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
