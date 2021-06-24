package com.ing.atm.testdata;

import com.ing.atm.model.Address;
import com.ing.atm.model.AtmLocation;
import com.ing.atm.model.GeoLocation;

/**
 * Test data factory class to build {@link AtmLocation}
 */
public class AtmLocationTestDataFactory {

    public static AtmLocation getAtmLocation(String cityName) {
        return AtmLocation.builder()
                .address(getAddress(cityName))
                .distance(10L)
                .type("GELDMAAT")
                .functionality("Geldautomaat")
                .build();
    }

    private static Address getAddress(String cityName) {
        return Address.builder()
                .houseNumber("30PBX")
                .postalCode("7396 AT")
                .street("First Street")
                .geoLocation(getGeoLocation())
                .city(cityName)
                .build();
    }

    private static GeoLocation getGeoLocation() {
        return GeoLocation.builder()
                .latitude("52.2829")
                .longitude("6.096641")
                .build();
    }
}