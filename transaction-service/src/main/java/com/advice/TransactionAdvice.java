package com.advice;

import com.common.BaseAdvice;
import com.common.Error;
import com.constant.ErrorCodes;
import com.controller.TransactionController;
import com.exception.TransactionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.common.ErrorUtil.constructError;

@ControllerAdvice(assignableTypes = TransactionController.class)
@Slf4j
public class TransactionAdvice extends BaseAdvice {
    public TransactionAdvice() {
        super(ErrorCodes.UNKNOWN_ERROR);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Error> handleException(TransactionNotFoundException e) {

        log.error("Transaction not found error occurred {}", e.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(constructError(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(constructError(ErrorCodes.VALIDATION_CONSTRAINT_ERROR,
                        generateFieldErrorMessage(e.getBindingResult().getFieldErrors())));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private static String generateFieldErrorMessage(List<FieldError> fieldErrors) {
        return fieldErrors
                .stream()
                .map(m -> m.getField() + " " + m.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }
}


