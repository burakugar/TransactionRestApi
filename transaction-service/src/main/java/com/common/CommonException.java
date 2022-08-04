package com.common;

public abstract class CommonException extends RuntimeException {
    private final int code;

    protected CommonException(String message, int code) {
        super(message);
        this.code = code;
    }

    protected CommonException(String message, int code, Throwable ex) {
        super(message, ex);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}

