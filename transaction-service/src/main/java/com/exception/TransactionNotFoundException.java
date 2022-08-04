package com.exception;

import com.common.CommonException;
import com.constant.ErrorCodes;

public class TransactionNotFoundException extends CommonException {
    public TransactionNotFoundException() {
        super("No transaction found!\"", ErrorCodes.TRANSACTION_NOT_FOUND);
    }
}
