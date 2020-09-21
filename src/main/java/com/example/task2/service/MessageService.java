package com.example.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * a class for getting the message from messages.properties with the key
 */

@Service
public class MessageService {

    @Autowired
    private MessageSource messageSource;


    public String getMessage(String key) {
        return messageSource.getMessage(key, null, null);
    }

    public String getCode(String key) {
        return messageSource.getMessage(key, null, null);
    }

    public String getMessage(String key, Object[] params) {
        return messageSource.getMessage(key, params, null);
    }

}
