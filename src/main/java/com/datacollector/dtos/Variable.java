package com.datacollector.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity(name = "variable")
public class Variable {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    private String name;

    private String format;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
