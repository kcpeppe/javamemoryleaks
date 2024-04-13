package com.kodewerk.db;

public class ClosingPriceDataSourceException extends Exception {

    public ClosingPriceDataSourceException() {
    }

    public ClosingPriceDataSourceException(String message) {
        super(message);
    }

    public ClosingPriceDataSourceException(Throwable cause) {
        super(cause);
    }

    public ClosingPriceDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}