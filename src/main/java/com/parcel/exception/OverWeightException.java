package com.parcel.exception;

import java.io.Serial;

public class OverWeightException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public OverWeightException(String message) {
        super(message);
    }
}
