package com.sg.floormastery.service;

/**
 *
 * @author lukem
 */
public class FMStateUnknownException extends Exception {

    public FMStateUnknownException(String message) {
        super(message);
    }

    public FMStateUnknownException(String message, Throwable cause) {
        super(message, cause);

    }

}
