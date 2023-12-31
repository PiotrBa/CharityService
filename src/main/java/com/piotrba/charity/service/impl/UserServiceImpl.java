package com.piotrba.charity.service.impl;

import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DonationRepository donationRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User registerAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_ADMIN");
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(Long id, User newUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setMobileNumber(newUser.getMobileNumber());
            user.setRole("ROLE_USER");
            user.setActive(true);
            user.setUsername(newUser.getUsername());
            if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            }
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUserByAdmin(Long id, User newUser) {
        Optional<User> optionalUserByAdmin = userRepository.findById(id);
        if (optionalUserByAdmin.isPresent()) {
            User user = optionalUserByAdmin.get();
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setMobileNumber(newUser.getMobileNumber());
            user.setRole(newUser.getRole());
            user.setActive(newUser.getActive());
            user.setUsername(newUser.getUsername());
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }


    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Donation> donations = donationRepository.findByUserUsername(user.getUsername());
        for (Donation donation:donations) {
            donationRepository.delete(donation);
        }
        userRepository.delete(user);
    }

    @Override
    public List<Donation> getUserDonations(String username) {
        return donationRepository.findByUserUsername(username);
    }
}
