package com.example.task2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class for returning responses for exceptions
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String code;
    private String description;
}
