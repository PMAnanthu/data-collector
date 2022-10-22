package com.datacollector.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResponse {
    private String error;
    private Boolean status;
}
