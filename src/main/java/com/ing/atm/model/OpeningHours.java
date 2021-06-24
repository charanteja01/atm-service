package com.ing.atm.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OpeningHours {
    int dayOfWeek;
    List<Timings> hoursList;

    @JsonCreator
    public OpeningHours(@JsonProperty("dayOfWeek") int dayOfWeek,
                        @JsonProperty("hours") List<Timings> hoursList) {

        this.dayOfWeek = dayOfWeek;
        this.hoursList = hoursList;

    }


}
