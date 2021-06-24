package com.ing.atm.controller;

import com.ing.atm.model.AtmLocation;
import com.ing.atm.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/v1/locations")
public class LocationController {

    private final LocationService locationService;

    /**
     * This method will fetch list of atm locations
     *
     * @return list of {@link AtmLocation}
     */
    @GetMapping
    public List<AtmLocation> getAtmLocations() {
        return locationService.getLocations();
    }

    /**
     * this method will be used to fetch the atm locations by city name
     * @param cityName to fetch the atm location
     * @return list of atm locations matched with city name
     */
    @GetMapping(value = "/{cityname}")
    public List<AtmLocation> getAtmLocation(@PathVariable(value = "cityname") String cityName) {
        return locationService.getLocation(cityName);
    }
}