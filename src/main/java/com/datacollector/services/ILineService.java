package com.datacollector.services;

import com.datacollector.dtos.Line;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILineService {
    public void preprocess(Line line, List<String> undefinedVariables);
}
