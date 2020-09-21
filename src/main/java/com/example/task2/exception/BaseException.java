package com.example.task2.exception;

import lombok.Data;

/**
 * All custom exceptions will be extended from this class
 */

@Data
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String messageKey;
    private Object[] params;

    public BaseException() {
    }

    public BaseException(String messageKey, Object... params) {
        this.messageKey = messageKey;
        this.params = params;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Object[] getParams() {
        return params;
    }
}
