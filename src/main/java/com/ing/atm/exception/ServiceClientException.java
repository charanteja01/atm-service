package com.ing.atm.exception;

/**
 * Class to hold service client exceptions
 */
public class ServiceClientException extends RuntimeException {

    /**
     * @param errorMessage contains error description
     */
    public ServiceClientException(String errorMessage) {
        super(errorMessage);
    }
}
