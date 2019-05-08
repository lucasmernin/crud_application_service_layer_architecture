package com.sg.floormastery.service;

/**
 *
 * @author lukem
 */
public class FMOrderUnknownException extends Exception {
    
     public FMOrderUnknownException(String message) {
        super(message);
    }

    public FMOrderUnknownException(String message, Throwable cause) {
        super(message, cause);
        
    }

}
