package com.ing.atm.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Locations {
    List<AtmLocation> atmLocations;
}
