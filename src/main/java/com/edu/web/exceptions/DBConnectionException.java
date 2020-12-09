package com.edu.web.exceptions;

public class DBConnectionException extends Exception {

    public DBConnectionException() {}

    public DBConnectionException(String message) {
        super(message);
    }

    public DBConnectionException(Throwable cause) {
        super(cause);
    }
}
