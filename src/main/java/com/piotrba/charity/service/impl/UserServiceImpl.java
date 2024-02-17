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
        return userRepository.findByActiveTrue();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setActive(false);
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
            if (newUser.getFirstName() != null) user.setFirstName(newUser.getFirstName());
            if (newUser.getLastName() != null) user.setLastName(newUser.getLastName());
            if (newUser.getEmail() != null) user.setEmail(newUser.getEmail());
            if (newUser.getMobileNumber() != null) user.setMobileNumber(newUser.getMobileNumber());
            if (newUser.getRole() != null) user.setRole(newUser.getRole());
            if (newUser.getActive() != null) user.setActive(newUser.getActive());
            if (newUser.getUsername() != null) user.setUsername(newUser.getUsername());
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }


    @Override
    public void deleteUserByAdmin(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Donation> donations = donationRepository.findDonationsByUserWithApprovalAndPackageReceived(user.getUsername());
        for (Donation donation:donations) {
            donationRepository.delete(donation);
        }
        userRepository.delete(user);
    }

    @Override
    public Optional<User> deleteUserByUser(Long id, User newUser) {
        Optional<User> optionalUserByUser = userRepository.findById(id);
            if (optionalUserByUser.isPresent()){
                User user = optionalUserByUser.get();
                if (newUser.getActive() != null) user.setActive(false);
                return Optional.of(userRepository.save(user));
            }
        return Optional.empty();
    }

    @Override
    public List<Donation> getUserDonations(String username) {
        return donationRepository.findDonationsByUserWithApprovalAndPackageReceived(username);
    }
}
