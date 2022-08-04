package com.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseAdvice {
    private static final Logger log = LoggerFactory.getLogger(BaseAdvice.class);
    private final int unknownCode;

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Error> handleException(RuntimeException e) {
        log.error("An unknown error occurred", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorUtil.constructError(this.unknownCode, e.getMessage()));
    }

    protected BaseAdvice(final int unknownCode) {
        this.unknownCode = unknownCode;
    }
}
