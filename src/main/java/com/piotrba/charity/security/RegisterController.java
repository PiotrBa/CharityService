package com.piotrba.charity.security;

import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import com.piotrba.charity.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserRepository userRepository;

    @GetMapping("/user")
    public String registerUserView(Model model){
        logger.info("Accessing user registration page");
        model.addAttribute("user", new User());
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        model.addAttribute("countAllDonations", donationService.countAllDonations());
        model.addAttribute("sumAllQuantities", donationService.sumAllQuantities());
        return "/security/register";
    }

    @PostMapping("/user")
    public String registerUser(User user){
        logger.info("Registering new user: {}", user.getUsername());
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String registerAdminView(Model model, Principal principal){
        logger.info("Accessing admin registration page for {}", principal.getName());
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("newAdmin", new User());
        return "/security/register-admin";
    }

    @PostMapping("/admin")
    public String registerAdmin(User user){
        logger.info("Registering new admin: {}", user.getUsername());
        userService.registerAdmin(user);
        return "redirect:/admin-profile-users";
    }
}
