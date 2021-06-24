package com.ing.atm.client;

import com.ing.atm.configuration.Config;
import com.ing.atm.exception.LocationException;
import com.ing.atm.exception.ServiceClientException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.ing.atm.testdata.AtmLocatorTestDataFactory.getAtmLocatorStringResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Test class for RestClient
 */
@RunWith(MockitoJUnitRunner.class)
public class RestClientTest {

    @InjectMocks
    private RestClient restClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Config config;

    @Before
    public void setUp() {
        when(config.getLocatorApiUrl()).thenReturn("https://www.ing.nl/api/locator/atms/");
    }
    @Test
    public void shouldGetResponseFromIngApi() {
        // Given
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(new ResponseEntity<>(getAtmLocatorStringResponse(), HttpStatus.OK));

        // When
        String actualResponse = restClient.getAtmLocatorResponse();

        // Then
        assertTrue(actualResponse.contains(")]}',"));
    }

    @Test
    public void shouldThrowLocationException() {
        // Given
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(new ResponseEntity<>("", HttpStatus.OK));

        // When
        Throwable exception = assertThrows(LocationException.class, () -> restClient.getAtmLocatorResponse());

        // Then
        assertEquals("No atms available", exception.getMessage());
    }

    @Test
    public void shouldThrowHttpClientErrorException() {
        // Given
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenThrow(HttpClientErrorException.class);

        // When
        Throwable exception = assertThrows(ServiceClientException.class, () -> restClient.getAtmLocatorResponse());

        // Then
        assertEquals("Error occurred while calling service", exception.getMessage());
    }
}