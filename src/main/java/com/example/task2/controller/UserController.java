package com.example.task2.controller;

import com.example.task2.rest.RegisterRequest;
import com.example.task2.rest.RegisterResponse;
import com.example.task2.service.UserService;
import com.example.task2.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * RestContoller class, all mappings are here
 */

@RestController
@RequestMapping(Constants.REST_CONTEXT_PATH)
@Api(value = Constants.REST_API_NAME)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param request
     * @return method for registering a user
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

}
