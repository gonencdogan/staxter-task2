package com.example.task2.rest;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * request for /userservice/register mapping
 * <p>
 * all fields are required
 */

@Data
@Builder
public class RegisterRequest {

    @NotEmpty(message = "firstName.null.message")
    @NotNull(message = "firstName.null.message")
    private String firstName;

    @NotEmpty(message = "lastName.null.message")
    @NotNull(message = "lastName.null.message")
    private String lastName;

    @NotEmpty(message = "userName.null.message")
    @NotNull(message = "userName.null.message")
    private String userName;

    @NotEmpty(message = "password.null.message")
    @NotNull(message = "password.null.message")
    private String password;

}
