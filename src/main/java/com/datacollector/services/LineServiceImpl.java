package com.datacollector.services;

import com.datacollector.constants.REGXConstants;
import com.datacollector.constants.VariableFunctions;
import com.datacollector.dtos.Line;
import com.datacollector.dtos.Variable;
import com.datacollector.repository.IVariableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.datacollector.constants.CharConstants.*;

public class LineServiceImpl implements ILineService {

    @Autowired
    private IVariableRepository variableRepository;

    @Override
    public void preprocess(Line line, List<String> undefinedVariables) {
        List<String> allVariables = new ArrayList<String>();
        getAllVariables(line, allVariables);
        replaceAllVariables(line, undefinedVariables, allVariables);
    }

    private void replaceAllVariables(Line line, List<String> undefinedVariables, List<String> allMatches) {
        line.setExpandedFormat(line.getFormat());
        for (int i = 0; i < allMatches.size(); i++) {
            String name = allMatches.get(i);
            Optional<Variable> varOpt = variableRepository.findByName(name);
            if (varOpt.isPresent()) {
                line.setExpandedFormat(line.getExpandedFormat().replace(VariableFunctions.CREATE_VARIABLE.apply(name),
                        VariableFunctions.CREATE_FORMAT.apply(varOpt.get())));
            } else {
                undefinedVariables.add(name);
            }
        }
    }

    private void getAllVariables(Line line, List<String> allMatches) {
        Matcher m = Pattern.compile(REGXConstants.VARIABLE_POINTER).matcher(line.getFormat());
        while (m.find()) {
            allMatches.add(m.group()
                    .replace(DOLLAR, WHITESPACE)
                    .replace(LEFT_BRACE, WHITESPACE)
                    .replace(RIGHT_BRACE, WHITESPACE));
        }
    }
}
