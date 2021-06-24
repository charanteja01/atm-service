package com.ing.atm.exception;

import com.ing.atm.model.ErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for {@link GlobalExceptionHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void shouldHandleClientException() {
        // Given
        String expectedMessage = "Unable to process response";
        ClientException exception = new ClientException(expectedMessage);
        // When
        ResponseEntity<ErrorResponse> actualResponse = globalExceptionHandler.handleClientException(exception);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualResponse.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(actualResponse.getBody()).getMessage());
    }

    @Test
    public void shouldHandleLocationException() {
        // Given
        String expectedMessage = "No atms found";
        LocationException exception = new LocationException(expectedMessage);

        // When
        ResponseEntity<ErrorResponse> actualResponse = globalExceptionHandler.handleLocationException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(actualResponse.getBody()).getMessage());
    }

    @Test
    public void shouldHandleServiceClientException() {
        // Given
        String expectedMessage = "Error occurred while calling service";
        ServiceClientException exception = new ServiceClientException(expectedMessage);
        // When
        ResponseEntity<ErrorResponse> actualResponse = globalExceptionHandler.handleServiceClientException(exception);

        // Then
        assertEquals(HttpStatus.BAD_GATEWAY, actualResponse.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(actualResponse.getBody()).getMessage());
    }

    @Test
    public void shouldHandleException() {
        // Given
        String expectedMessage = "Internal server error";
        Exception exception = new Exception(expectedMessage);
        // When
        ResponseEntity<ErrorResponse> actualResponse = globalExceptionHandler.handleException(exception);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(actualResponse.getBody()).getMessage());
    }
}