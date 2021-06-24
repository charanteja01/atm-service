package com.ing.atm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    @JsonProperty("message")
    String message;
}
