package com.piotrba.charity.security;

import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import com.piotrba.charity.service.UserService;
import com.piotrba.charity.service.impl.EmailSenderServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserRepository userRepository;
    private final EmailSenderServiceImpl emailSenderService;

    private String getAppUrl(HttpServletRequest request){
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

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
    public String registerUser(User user, HttpServletRequest request){
        logger.info("Registering new user: {}", user.getUsername());
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        User savedUser = userService.registerUser(user);
        String activationLink = getAppUrl(request) + "/register/activate?token=" + token;
        emailSenderService.sendActivationEmail(savedUser.getEmail(), activationLink, savedUser);

        return "redirect:/register/sent-email-inf";
    }

    @GetMapping("/sent-email-inf")
    public String sentEmailInf(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        model.addAttribute("countAllDonations", donationService.countAllDonations());
        model.addAttribute("sumAllQuantities", donationService.sumAllQuantities());
        return "/security/sentEmail-inf";
    }



    @GetMapping("/activate")
    public String activateAccount(Model model, @RequestParam String token) {
        model.addAttribute("user", new User());
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        model.addAttribute("countAllDonations", donationService.countAllDonations());
        model.addAttribute("sumAllQuantities", donationService.sumAllQuantities());
        Optional<User> userOptional = userRepository.findByToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActive(true);
            user.setToken(null);
            userRepository.save(user);
            return "/security/accountActivated";
        }
        return "/security/errorAccount";
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
