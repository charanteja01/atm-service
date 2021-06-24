package com.ing.atm.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AtmLocation {

    Address address;
    Long distance;
    String functionality;
    String type;
    List<OpeningHours> openingHours;

    @JsonCreator
    public AtmLocation(@JsonProperty("address") Address address,
                       @JsonProperty("distance") Long distance,
                       @JsonProperty("functionality") String functionality,
                       @JsonProperty("type") String type,
                       @JsonProperty("openinghours") List<OpeningHours> openingHours) {

        this.address = address;
        this.distance = distance;
        this.functionality = functionality;
        this.type = type;
        this.openingHours = openingHours;
    }
}
