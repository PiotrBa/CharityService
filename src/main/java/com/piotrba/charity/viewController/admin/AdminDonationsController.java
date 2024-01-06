package com.piotrba.charity.viewController.admin;

import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin-profile-donations")
@AllArgsConstructor
public class AdminDonationsController {

    private static final Logger logger = LoggerFactory.getLogger(AdminDonationsController.class);

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;

    @GetMapping()
    public String showForm(Model model, Principal principal){
        logger.info("Accessing admin profile donations page");
        String userName = principal.getName();
        model.addAttribute("user", userRepository.getByUsername(userName));
        model.addAttribute("donations", donationRepository.findAllWithUser());
        logger.info("Donations page loaded successfully for user: {}", userName);
        return "admin/donations/admin-profile-donations";
    }
}
