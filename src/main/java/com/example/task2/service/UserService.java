package com.example.task2.service;

import com.example.task2.entity.User;
import com.example.task2.exception.UserAlreadyExistsException;
import com.example.task2.repository.UserRepository;
import com.example.task2.rest.RegisterRequest;
import com.example.task2.rest.RegisterResponse;
import com.example.task2.utils.CommonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is responsible for creating and retrieving users from arrayList ( dbList )
 * all rules for creating users are implemented here
 */

@Service
public class UserService implements UserRepository {

    List<User> dbList = new ArrayList<>();
    private static int id = 0;

    /**
     * @param request
     * @return method for registering user
     */
    public ResponseEntity<RegisterResponse> registerUser(RegisterRequest request) {

        User user = this.findByUserName(request.getUserName()); // search for userName in the dbList

        // if userName is not taken create a new user
        if (user == null) {

            User newUser = User.builder()
                    .userName(request.getUserName())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .plainTextPassword(request.getPassword())
                    .hashedPassword(CommonUtils.encrypte(request.getPassword()))
                    .id(String.valueOf(id))
                    .build();

            user = this.createUser(newUser);
            id++;

        } else { // if userName is taken throw error

            throw new UserAlreadyExistsException();

        }

        // if everything goes perfect return the create and return response
        return createReponse(user);

    }

    /**
     * @param user
     * @return creates a response object for new added user
     */
    public ResponseEntity<RegisterResponse> createReponse(User user) {

        RegisterResponse response = RegisterResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .userName(user.getUserName())
                .build();

        return new ResponseEntity<RegisterResponse>(response, HttpStatus.OK);

    }

    /**
     * @param user
     * @return adds the user into the arrayList
     */
    @Override
    public User createUser(User user) {
        dbList.add(user);
        return user;
    }

    /**
     * @param userName
     * @return method for searching user with userName in the arrayList
     */
    @Override
    public User findByUserName(String userName) {
        User user = dbList.stream().filter(p -> p.getUserName().equals(userName)).findFirst().orElse(null);
        if (user != null) {
            return user;
        }
        return null;
    }
}
