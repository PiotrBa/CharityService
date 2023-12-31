package com.piotrba.charity.viewController.user;

import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.InstitutionService;
import com.piotrba.charity.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/user-profile")
public class UserProfileController {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final UserService userService;
    private final InstitutionService institutionService;

    @GetMapping()
    public String getProfileView(Model model, Principal principal) {
        logger.info("Accessing user profile for user: {}", principal.getName());
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("donations", donationRepository.findByUserUsername(principal.getName()));
        model.addAttribute("countUserDonations", donationRepository.countDonationsByUserUsername(principal.getName()));
        model.addAttribute("sumUserQuantities", donationRepository.sumQuantitiesByUserUsername(principal.getName()));
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        return "user/user-profile";
    }

    @GetMapping("/edit")
    public String editProfileView(Model model, @RequestParam Long id, Principal principal) {
        logger.info("Editing profile view for user: {}", principal.getName());
        model.addAttribute("countUserDonations", donationRepository.countDonationsByUserUsername(principal.getName()));
        model.addAttribute("sumUserQuantities", donationRepository.sumQuantitiesByUserUsername(principal.getName()));
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        }
        return "user/user-profile-edit";
    }

    @PostMapping("/edit")
    public String editProfile(User user, @RequestParam Long id) {
        logger.info("Updating profile for user ID: {}", id);
        userService.updateUser(id, user);
        return "redirect:/user-profile";
    }

    @GetMapping("/delete")
    public String deleteProfileView(Model model, @RequestParam Long id, Principal principal) {
        logger.info("Viewing delete profile for user ID: {}", id);
        model.addAttribute("countUserDonations", donationRepository.countDonationsByUserUsername(principal.getName()));
        model.addAttribute("sumUserQuantities", donationRepository.sumQuantitiesByUserUsername(principal.getName()));
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        }
        return "user-profile-delete";
    }

    @PostMapping("/delete")
    public String deleteProfile(@RequestParam Long id) {
        logger.info("Deleting profile for user ID: {}", id);
        userService.deleteUser(id);
        return "redirect:/index";
    }
}
