package com.example.task2.exception;

import lombok.Data;

@Data
public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String messageKey) {
        super(messageKey);
    }

    public UserAlreadyExistsException(String messageKey, Object... params) {
        super(messageKey, params);
    }
}
