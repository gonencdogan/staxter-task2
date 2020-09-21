package com.example.task2.repository;

import com.example.task2.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User createUser(User user);

    User findByUserName(String userName);

}
