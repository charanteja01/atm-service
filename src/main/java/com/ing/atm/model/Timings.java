package com.ing.atm.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Timings {
    String hourFrom;
    String hourTo;

    @JsonCreator
    public Timings(@JsonProperty("hourFrom") String hourFrom,
                   @JsonProperty("hourTo") String hourTo) {

        this.hourFrom = hourFrom;
        this.hourTo = hourTo;

    }


}
