package com.edu.web.exceptions;

public class DBConnectionException extends Exception {

    private DBConnectionException() {}

    public DBConnectionException(String message) {
        super(message);
    }
}
