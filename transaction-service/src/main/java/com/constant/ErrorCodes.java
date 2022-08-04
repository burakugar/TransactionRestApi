package com.constant;

public final class ErrorCodes {
    private ErrorCodes() {
        throw new IllegalStateException("Constant Class");
    }

    public static final Integer TRANSACTION_NOT_FOUND = 10000;
    public static final Integer VALIDATION_CONSTRAINT_ERROR= 10001;
    public static final Integer UNKNOWN_ERROR = 19999;
}
