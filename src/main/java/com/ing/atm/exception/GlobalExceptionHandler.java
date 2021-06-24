package com.ing.atm.exception;

import com.ing.atm.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class to handle multiple exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * This method will convert {@link LocationException} into {@link ErrorResponse}
     * @param exception contains cause of the exceptions etc.
     * @return instance of {@link ErrorResponse}
     */
    @ExceptionHandler(LocationException.class )
    public ResponseEntity<ErrorResponse> handleLocationException(LocationException exception) {
        log.debug("In side LocationException handling");
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method will convert {@link ClientException} into {@link ErrorResponse}
     * @param exception contains cause of the exceptions etc.
     * @return instance of {@link ErrorResponse}
     */
    @ExceptionHandler(ClientException.class )
    public ResponseEntity<ErrorResponse> handleClientException(ClientException exception) {
        log.debug("In side ClientException handling");
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * This method will convert {@link Exception} into {@link ErrorResponse}
     * @param exception contains cause of the exceptions etc.
     * @return instance of {@link ErrorResponse}
     */
    @ExceptionHandler(Exception.class )
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.debug("In side Exception handling");
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method will convert {@link ServiceClientException} into {@link ErrorResponse}
     * @param exception contains cause of the exceptions etc.
     * @return instance of {@link ErrorResponse}
     */
    @ExceptionHandler(ServiceClientException.class )
    public ResponseEntity<ErrorResponse> handleServiceClientException(ServiceClientException exception) {
        log.debug("In side ServiceClientException handling");
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.BAD_GATEWAY);
    }
}