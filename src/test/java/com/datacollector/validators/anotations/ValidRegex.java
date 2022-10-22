package com.datacollector.validators.anotations;

import com.datacollector.validators.REGEXValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = REGEXValidator.class)
@Target( { ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegex {
        public String message() default "Not a valid Regex";
}  