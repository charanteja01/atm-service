package com.ing.atm.exception;

/**
 * Class to handle location exceptions
 */
public class LocationException extends RuntimeException{

    /**
     * @param errorMessage to return in error response
     */
    public LocationException(String errorMessage) {
        super(errorMessage);
    }
}
