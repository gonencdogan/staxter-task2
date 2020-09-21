package com.example.task2.rest;

import lombok.Builder;
import lombok.Data;

/**
 * reponse class for /userservice/register mapping
 */

@Data
@Builder
public class RegisterResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;

}
