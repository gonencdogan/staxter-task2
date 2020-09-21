package com.example.task2.entity;

import lombok.Builder;
import lombok.Data;

/**
 * entity/dao class for user
 */

@Data
@Builder
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String plainTextPassword;
    private String hashedPassword;

}
