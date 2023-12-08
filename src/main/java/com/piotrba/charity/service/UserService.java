package com.piotrba.charity.service;

import com.piotrba.charity.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    User registerUser(User user);
    Optional<User> updateUser(Long id, User user);
    void deleteUser(Long id);
}