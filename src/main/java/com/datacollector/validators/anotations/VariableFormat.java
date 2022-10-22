package com.datacollector.validators.anotations;

import com.datacollector.validators.VariableFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VariableFormatValidator.class)
@Target( { ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface VariableFormat {
        public String message() default "Not a valid Variable";
        //represents group of constraints
        public Class<?>[] groups() default {};
        //represents additional information about annotation
        public Class<? extends Payload>[] payload() default {};
}  