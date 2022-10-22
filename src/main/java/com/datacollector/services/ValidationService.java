package com.datacollector.services;

import com.datacollector.constants.REGXConstants;
import com.datacollector.dtos.Line;
import com.datacollector.dtos.ValidationResponse;
import com.datacollector.dtos.Variable;
import com.datacollector.repository.IVariableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
public class ValidationService implements IValidator {

    @Autowired
    private IVariableRepository variableRepository;

    public final Function<Variable, Boolean> VALIDATE_NAME = (var) ->
            Pattern.compile(REGXConstants.VARIABLE).matcher(var.getName()).matches();

    public final Function<Variable, String> NAME_IS_NOT_VALID = (var) -> "Name is not Valid: " + var.getName() + " " +
            "allowed char [a-zA-Z0-9_]";
    public final Function<Variable, String> DUPLICATE = (var) -> "Duplicate Name : " + var.getName();

    @Override
    public ValidationResponse validate(Variable variable) {
        try {
            Pattern.compile(variable.getFormat());
            log.debug(variable.getFormat() + " is valid");
            Optional<Variable> variableOpt = variableRepository.findByName(variable.getName());
            if (variableOpt.isPresent()) {
                log.debug(variable.getName()+ " is already in use");
                return ValidationResponse.builder().status(false).error(DUPLICATE.apply(variable)).build();
            } else {
                if (VALIDATE_NAME.apply(variable)) {
                    log.debug(variable.getName() + " is valid");
                    return ValidationResponse.builder().status(true).build();
                }
                log.debug(variable.getName() + " is not valid");
                return ValidationResponse.builder().status(false).error(NAME_IS_NOT_VALID.apply(variable)).build();
            }
        } catch (PatternSyntaxException exception) {
            return ValidationResponse.builder().status(false).error(exception.getMessage()).build();
        }
    }

    @Override
    public ValidationResponse validate(Line line) {
        try {
            Pattern.compile(line.getExpandedFormat());
            return ValidationResponse.builder().status(true).build();
        } catch (PatternSyntaxException exception) {
            return ValidationResponse.builder().status(false).error(exception.getMessage()).build();
        }
    }
}
/*
 *
 *
 *
 * */