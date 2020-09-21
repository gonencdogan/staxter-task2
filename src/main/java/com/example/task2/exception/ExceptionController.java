package com.example.task2.exception;


import com.example.task2.service.MessageService;
import com.example.task2.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * All exceptions are handled in this class
 */


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageService messageService;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", Constants.BAD_REQUEST_CODE);

        //Get the error from request
        String error = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .findFirst().orElse(null);

        String messageKey = StringUtils.isEmpty(error) ? null : error; // if error is not null get the key
        body.put("description", messageService.getMessage(messageKey)); // get the error message from properties

        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ExceptionResponse handleUserAlreadyExistsException(final UserAlreadyExistsException ex) {
        return new ExceptionResponse(Constants.USER_ALREADY_EXISTS_CODE, messageService.getMessage("user.already.exists.error"));
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ExceptionResponse handleThrowable(final Throwable ex) {
        ex.printStackTrace();
        return new ExceptionResponse(Constants.UNHANDLED_EXCEPTION_CODE, messageService.getMessage("unhandled.exception.error"));
    }


}
