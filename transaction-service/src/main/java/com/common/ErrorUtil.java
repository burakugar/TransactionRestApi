package com.common;

import java.util.Date;

public final class ErrorUtil {
    private ErrorUtil() {
        throw new IllegalArgumentException("Utility class");
    }

    public static Error constructError(CommonException e) {
        return Error.builder().code(e.getCode()).error(e.getMessage()).timestamp((new Date()).getTime()).build();
    }

    public static Error constructError(final Integer code, final String error) {
        return Error.builder().code(code).error(error).timestamp((new Date()).getTime()).build();
    }
}
