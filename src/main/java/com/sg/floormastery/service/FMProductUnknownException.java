package com.sg.floormastery.service;

/**
 *
 * @author lukem
 */
public class FMProductUnknownException extends Exception {

    public FMProductUnknownException(String message) {
        super(message);
    }

    public FMProductUnknownException(String message, Throwable cause) {
        super(message, cause);

    }

}
