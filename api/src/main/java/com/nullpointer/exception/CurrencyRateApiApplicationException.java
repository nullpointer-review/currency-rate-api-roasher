package com.nullpointer.exception;

/**
 * Created by pyurkin on 10/9/2015.
 */
public class CurrencyRateApiApplicationException extends Exception {

    public CurrencyRateApiApplicationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CurrencyRateApiApplicationException(String message) {
        super(message);
    }

}
