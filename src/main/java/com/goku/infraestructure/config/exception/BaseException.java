package com.goku.infraestructure.config.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BaseException extends RuntimeException {

    public BaseException(String errorMsg) {
        super(errorMsg);
    }

    public BaseException(Throwable e) {
        super(e);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}