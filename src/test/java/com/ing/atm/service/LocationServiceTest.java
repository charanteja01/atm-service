package com.ing.atm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.atm.client.RestClient;
import com.ing.atm.exception.LocationException;
import com.ing.atm.model.AtmLocation;
import com.ing.atm.testdata.AtmLocatorTestDataFactory;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.ing.atm.testdata.AtmLocationTestDataFactory.getAtmLocation;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link LocationService}
 */
@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private LocationService locationService;

    private List<AtmLocation> expectedAtmLocations = null;

    @Before
    @SneakyThrows
    public void setUp(){
        expectedAtmLocations = asList(getAtmLocation("VROOMSHOOP"), getAtmLocation("Zwolle"));
        when(restClient.getAtmLocatorResponse()).thenReturn(AtmLocatorTestDataFactory.getAtmLocatorStringResponse());
        when(objectMapper.readValue(anyString(), eq(AtmLocation[].class))).thenReturn((AtmLocation[]) expectedAtmLocations.toArray());
    }

    @Test
    public void shouldReturnAtmLocations() {
        //When
        List<AtmLocation> actualAtmLocations = locationService.getLocations();

        //Then
        assertEquals(expectedAtmLocations, actualAtmLocations);
    }

    @Test
    public void shouldReturnAtmLocation() {
        //Given
        String cityName = "Zwolle";
        expectedAtmLocations = asList(getAtmLocation("VROOMSHOOP"), getAtmLocation("Zwolle"));

        //When
        List<AtmLocation> actualAtmLocations = locationService.getLocation(cityName);

        //Then
        assertEquals(expectedAtmLocations.get(1), actualAtmLocations.get(0));
    }

    @Test
    public void shouldThrowLocationException() {
        //Given
        String cityName = "Zwolleddddds";
        String expectedErrorMessage = "No Atms found in Zwolleddddds";

        //When
        Throwable exception = assertThrows(LocationException.class, () -> locationService.getLocation(cityName));

        //Then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}