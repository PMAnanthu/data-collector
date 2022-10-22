package com.datacollector.controllers;

import com.datacollector.constants.VariableFunctions;
import com.datacollector.dtos.Variable;
import com.datacollector.exceptions.ResourceNotFoundException;
import com.datacollector.repository.IVariableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v0/variable")
public class VariableController {

    @Autowired
    private IVariableRepository repository;

    @GetMapping("")
    public Variable findById(@RequestParam("id") Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(VariableFunctions.RESOURCES_NOT_FOUNT.apply(id)));
    }

    @GetMapping("/all")
    public List<Variable> findAll() {
        return repository.findAll();
    }

    @GetMapping("/all-by-name")
    public List<Variable> findByName(@RequestParam("name") String name) throws ResourceNotFoundException {
        return repository.findByNameContaining(name);
    }

    @PostMapping("")
    public Variable save(@Valid @RequestBody Variable variable)  {
        log.debug("Request to insert data: " + variable.toString());
        return repository.save(variable);
    }

    @PutMapping("")
    public Variable update(@RequestParam("id") Long id, @Valid @RequestBody Variable variable) throws ResourceNotFoundException {
        Variable variableOpt = findById(id);
        variableOpt.setId(id);
        variableOpt.setUpdatedAt(LocalDateTime.now());
        variableOpt.setFormat(variable.getFormat());
        variableOpt.setName(variable.getName());
        return repository.save(variableOpt);
    }

    @DeleteMapping("")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
