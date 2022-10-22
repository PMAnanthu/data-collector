package com.datacollector.controllers;

import com.datacollector.constants.VariableFunctions;
import com.datacollector.dtos.Line;
import com.datacollector.dtos.ValidationResponse;
import com.datacollector.exceptions.InvalidDataException;
import com.datacollector.exceptions.ResourceNotFoundException;
import com.datacollector.repository.ILineRepository;
import com.datacollector.services.ILineService;
import com.datacollector.services.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v0/line")
public class LineController {

    @Autowired
    private ILineRepository repository;

    @Autowired
    private IValidator validator;

    @Autowired
    private ILineService lineService;

    @GetMapping("")
    public Line findById(@RequestParam("id") Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(VariableFunctions.RESOURCES_NOT_FOUNT.apply(id)));
    }

    @GetMapping("/all")
    public List<Line> findAll() {
        return repository.findAll();
    }

    @GetMapping("/contains")
    public List<Line> findByName(@RequestParam("name") String name) throws ResourceNotFoundException {
        return repository.findByFormatContaining(name);
    }

    @PostMapping("")
    public Line save(@Valid @RequestBody Line line) throws InvalidDataException {
        List<String> undefinedVariables = new ArrayList<>();
        lineService.preprocess(line, undefinedVariables);
        if (undefinedVariables.size() > 0) {
            throw new InvalidDataException(String.join(",", undefinedVariables) + " variables are not defined");
        }
        ValidationResponse valid = validator.validate(line);
        if (valid.getStatus()) {
            return repository.save(line);
        }
        throw new InvalidDataException(valid.getError());
    }

    @PutMapping("")
    public Line update(@RequestParam("id") Long id, @Valid @RequestBody Line line) throws ResourceNotFoundException, InvalidDataException {
        List<String> undefinedVariables = new ArrayList<>();
        lineService.preprocess(line, undefinedVariables);
        if (undefinedVariables.size() > 0) {
            throw new InvalidDataException(String.join(",", undefinedVariables) + "variables are not defined");
        }
        ValidationResponse valid = validator.validate(line);
        if (valid.getStatus()) {
            Line lineOpt = findById(id);
            lineOpt.setId(id);
            lineOpt.setUpdatedAt(LocalDateTime.now());
            lineOpt.setFormat(line.getFormat());
            lineOpt.setExpandedFormat(line.getExpandedFormat());
            return repository.save(lineOpt);
        }
        throw new InvalidDataException(valid.getError());
    }

    @DeleteMapping("")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
