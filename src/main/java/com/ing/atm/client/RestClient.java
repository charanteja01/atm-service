package com.ing.atm.client;

import com.ing.atm.configuration.Config;
import com.ing.atm.exception.LocationException;
import com.ing.atm.exception.ServiceClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Client class to call atm locator service
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestClient {

    private final RestTemplate restTemplate;
    private final Config config;

    /**
     * This method will be used to call atm locator service
     * @return string contains atm location details
     */
    public String getAtmLocatorResponse() {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(config.getLocatorApiUrl(), String.class);
            if (null != responseEntity.getBody() && !responseEntity.getBody().isEmpty()) {
                return responseEntity.getBody();
            } else {
                throw new LocationException("No atms available");
            }
        } catch (HttpClientErrorException exception) {
            throw new ServiceClientException("Error occurred while calling service");
        }
    }
}