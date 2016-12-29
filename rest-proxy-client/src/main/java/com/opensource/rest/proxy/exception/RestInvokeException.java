package com.opensource.rest.proxy.exception;

/**
 * Created by Aaron on 26/12/2016.
 */
public class RestInvokeException extends RuntimeException {
    public RestInvokeException() {
        super();
    }

    public RestInvokeException(String message) {
        super(message);
    }

    public RestInvokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestInvokeException(Throwable cause) {
        super(cause);
    }

    public RestInvokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
