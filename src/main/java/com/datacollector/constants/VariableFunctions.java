package com.datacollector.constants;

import com.datacollector.dtos.Variable;

import java.util.function.Function;

public class VariableFunctions {
    public static final Function<Long, String> RESOURCES_NOT_FOUNT = (id) -> "Variable Not fount with id : " + id.toString();
    public static final Function<String, String> CREATE_VARIABLE = (var) -> String.format("${%s}", var);
    public static final Function<Variable, String> CREATE_FORMAT = (var) -> String.format("(?<%s>$s)",
            var.getName(), var.getFormat());

}
