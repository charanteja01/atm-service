package com.ing.atm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.atm.client.RestClient;
import com.ing.atm.exception.ClientException;
import com.ing.atm.exception.LocationException;
import com.ing.atm.model.AtmLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Service class for atm location
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocationService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    /**
     * This method will fetch list of atm locations
     *
     * @return list of {@link AtmLocation}
     * @throws ClientException to process response exception
     */
    @Cacheable(value = "atmLocationsCache")
    public List<AtmLocation> getLocations() {
        String locatorResponse = restClient.getAtmLocatorResponse().substring(6);
        try {
            return asList(objectMapper.readValue(locatorResponse, AtmLocation[].class));
        } catch (JsonProcessingException e) {
            throw new ClientException("Unable to process the response");
        }
    }

    /**
     * this method will be used to fetch atm locations by city name
     * @param cityName to fetch the atm location
     * @return list of atm locations matched with city name
     * @throws LocationException if no atm found in the location
     */
    @Cacheable(value = "atmLocationsCache", key= "#cityName")
    public List<AtmLocation>getLocation(String cityName) {
        List<AtmLocation> atmLocations = getLocations();
        List<AtmLocation> atmLocationsByCityName = atmLocations.stream()
                .filter(atmDetails -> cityName.equals(atmDetails.getAddress().getCity()))
                .collect(Collectors.toList());
        if (atmLocationsByCityName.isEmpty()) {
            String errorMessage = String.format("No Atms found in %s", cityName);
            throw new LocationException(errorMessage);
        }
        return atmLocationsByCityName;
    }
}