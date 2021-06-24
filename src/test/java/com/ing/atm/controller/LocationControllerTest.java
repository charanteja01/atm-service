package com.ing.atm.controller;

import com.ing.atm.model.AtmLocation;
import com.ing.atm.service.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.ing.atm.testdata.AtmLocationTestDataFactory.getAtmLocation;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test class for {@link LocationController}
 */
@RunWith(MockitoJUnitRunner.class)
public class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @Test
    public void shouldGetAtmLocations() {
        // Given
        List<AtmLocation> expectedAtmLocations = asList(getAtmLocation("VROOMSHOOP"), getAtmLocation("Zwolle"));
        when(locationService.getLocations()).thenReturn(expectedAtmLocations);

        // When
        List<AtmLocation> actualResponse = locationController.getAtmLocations();

        // Then
        assertEquals(expectedAtmLocations, actualResponse);
        verify(locationService, times(1)).getLocations();
    }

    @Test
    public void shouldGetAtmLocationByCityName() {
        // Given
        String cityName = "Zwolle";
        List<AtmLocation> expectedLocations = Collections.singletonList(getAtmLocation("Zwolle"));
        when(locationService.getLocation(anyString())).thenReturn(expectedLocations);

        // When
        List<AtmLocation> actualResponse = locationController.getAtmLocation(cityName);

        // Then
        assertEquals(expectedLocations, actualResponse);
        verify(locationService, times(1)).getLocation(cityName);
    }
}