package com.datacollector.services;

import com.datacollector.dtos.Line;
import com.datacollector.dtos.ValidationResponse;
import com.datacollector.dtos.Variable;
import org.springframework.stereotype.Service;

@Service
public interface IValidator {
    ValidationResponse validate(Variable variable);

    ValidationResponse validate(Line line);
}
