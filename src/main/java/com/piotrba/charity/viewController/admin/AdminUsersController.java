package com.piotrba.charity.viewController.admin;

import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@AllArgsConstructor
@RequestMapping("/admin-profile-users")
public class AdminUsersController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUsersController.class);

    private final UserRepository userRepository;
    private final UserService userService;
    private final DonationRepository donationRepository;

    @GetMapping()
    public String getAdminProfileView(Model model, Principal principal) {
        logger.info("Accessing admin profile users page");
        List<User> users = userRepository.findAll();
        List<User> admins = new ArrayList<>();
        List<User> regularUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getRole().equals("ROLE_ADMIN")) {
                admins.add(user);
            } else if (user.getRole().equals("ROLE_USER")) {
                regularUsers.add(user);
            }
        }
        Map<Long, Integer> userDonationsSum = new HashMap<>();
        for (User user : users) {
            Integer sum = Math.toIntExact(donationRepository.countDonationsByUserUsername(user.getUsername()));
            userDonationsSum.put(user.getId(), sum);
        }
        model.addAttribute("currentUser", userRepository.getByUsername(principal.getName()));
        model.addAttribute("admins", admins);
        model.addAttribute("users", regularUsers);
        model.addAttribute("userDonationsSum", userDonationsSum);
        return "admin/users/admin-profile-users";
    }

    @GetMapping("/update")
    public String editUserView(Model model, @RequestParam Long id, Principal principal){
        logger.info("Editing user view with ID: {}", id);
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()){
            model.addAttribute("user", userOptional.get());
        }
        return "admin/users/admin-profile-users-edit";
    }

    @PostMapping("/update")
    public String editUser(User user, @RequestParam Long id){
        logger.info("Updating user with ID: {}", id);
        userService.updateUserByAdmin(id, user);
        return "redirect:/admin-profile-users";
    }

    @GetMapping("/delete")
    public String deleteUserView(Model model, @RequestParam Long id, Principal principal){
        logger.info("Deleting user view for ID: {}", id);
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()){
            model.addAttribute("user", userOptional.get());
        }
        return "admin/users/admin-profile-users-delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id){
        logger.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        return "redirect:/admin-profile-users";
    }
}
