package com.kunal52.exception;

public class PairingException extends Exception {
    public PairingException(String message) {
        super(message);
    }

    public PairingException(String message, Exception e) {
        super(message, e);
    }
}
