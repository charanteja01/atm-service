package com.ing.atm.exception;

/**
 * Class to hold client exceptions
 */
public class ClientException extends RuntimeException {

    /**
     * @param errorMessage contains error description
     */
    public ClientException(String errorMessage) {
        super(errorMessage);
    }
}
