package com.TelstraAndroidTest.utils;

/**
 * Enum constant for server onSuccess error codes.
 */
public enum ErrorType {

    BadRequest(400),
    Unauthorized(401),
    InternalServerError(500),
    ServiceUnavailable(503),
    NoError(0);

    private int value;

    ErrorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
