package com.piotrba.charity.security;

import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import com.piotrba.charity.service.impl.EmailSenderServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/reset-password")
public class ResetPasswordController {

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final UserRepository userRepository;
    private final EmailSenderServiceImpl emailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @GetMapping
    public String resetPasswordView(Model model) {
        logger.info("Displaying reset password view");
        model.addAttribute("sumAllQuantities", donationService.sumAllQuantities());
        model.addAttribute("countAllDonations", donationService.countAllDonations());
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        return "security/resetPassword/reset-password";
    }

    @PostMapping
    public String resetPassword(String email, HttpServletRequest request) {
        logger.info("Initiating password reset for email: {}", email);
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByEmail(email));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getActive()) {
                String resetToken = generateResetToken();
                user.setResetToken(resetToken);
                userRepository.save(user);
                logger.info("Reset token generated and saved for user: {}", user.getUsername());

                String resetLink = getAppUrl(request) + "/reset-password/confirm?token=" + resetToken;
                emailSenderService.sendResetPasswordEmail(user.getEmail(), resetLink, user);
                return "redirect:/reset-password/sent";
            } else {
                logger.warn("Attempted password reset for inactive user: {}", email);
            }
        } else {
            logger.warn("Email not found for password reset: {}", email);
        }
        return "redirect:/reset-password/error";
    }

    @GetMapping("/confirm")
    public String confirmReset(@RequestParam String token, Model model) {
        logger.info("Password reset confirmation with token: {}", token);
        Optional<User> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isPresent()) {
            model.addAttribute("token", token);
            return "security/resetPassword/reset-password-confirm";
        } else {
            logger.warn("Invalid token for password reset confirmation: {}", token);
            return "redirect:/reset-password/error";
        }
    }

    @PostMapping("/confirm")
    public String processReset(@RequestParam String token, @RequestParam String newPassword) {
        logger.info("Processing password reset with token: {}", token);
        Optional<User> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isPresent() && newPassword != null && !newPassword.isEmpty()) {
            User user = userOptional.get();
            user.setPassword(encodePassword(newPassword));
            user.setResetToken(null);
            userRepository.save(user);
            logger.info("Password reset successful for user: {}", user.getUsername());
            return "redirect:/reset-password/success";
        } else {
            logger.warn("Failed password reset attempt with token: {}", token);
            return "redirect:/reset-password/error";
        }
    }

    @GetMapping("/sent")
    public String resetPasswordSent() {
        logger.info("Displaying password reset sent page");
        return "security/resetPassword/reset-password-sent";
    }

    @GetMapping("/success")
    public String resetPasswordSuccess() {
        logger.info("Displaying password reset success page");
        return "security/resetPassword/reset-password-success";
    }

    @GetMapping("/error")
    public String resetPasswordError() {
        logger.info("Displaying password reset error page");
        return "security/resetPassword/reset-password-error";
    }
}
