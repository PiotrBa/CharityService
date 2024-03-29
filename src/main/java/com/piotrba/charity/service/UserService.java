package com.piotrba.charity.service;

import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    User registerUser(User user);
    User registerAdmin(User user);
    Optional<User> updateUser(Long id, User user);
    Optional<User> updateUserByAdmin(Long id, User user);
    void deleteUserByAdmin(Long id);
    Optional<User> deleteUserByUser(Long id, User newUser);
    List<Donation> getUserDonations(String username);
}
