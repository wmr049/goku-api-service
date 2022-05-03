package com.goku.infraestructure.config.exception;

public class BaseServiceException extends BaseException {

    public BaseServiceException(final String message) {
        super(message);
    }

    public BaseServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
