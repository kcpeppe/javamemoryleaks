package com.kodewerk.db;

public class DataBaseException extends Exception {

    public DataBaseException() {}

    public DataBaseException(String s) {
        super(s);
    }

    public DataBaseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DataBaseException(Throwable throwable) {
        super(throwable);
    }
}
