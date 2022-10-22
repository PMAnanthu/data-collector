package com.datacollector.dtos;

import com.datacollector.validators.anotations.VariableFormat;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Entity(name = "variable")
@ToString
public class Variable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Name should not be blank")
    @Pattern(regexp = "[a-zA-Z0-9_]*", message = "Name only allow in JAVA variable format")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Format should not be blank")
    @VariableFormat(message = "Format is not valid")
    @Column(nullable = false)
    private String format;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
