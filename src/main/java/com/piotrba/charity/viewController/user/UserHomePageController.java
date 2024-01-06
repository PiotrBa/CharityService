package com.piotrba.charity.viewController.user;

import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user-homepage")
@AllArgsConstructor
public class UserHomePageController {

    private static final Logger logger = LoggerFactory.getLogger(UserHomePageController.class);

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final InstitutionService institutionService;

    @GetMapping()
    public String getProfileView(Model model, Principal principal){
        logger.info("Accessing user homepage");
        String userName = principal.getName();
        model.addAttribute("user", userRepository.getByUsername(userName));
        model.addAttribute("donations", donationRepository.findByUserUsername(userName));
        model.addAttribute("countDonations", donationRepository.countDonationsByUserUsername(userName));
        model.addAttribute("sumQuantities", donationRepository.sumQuantitiesByUserUsername(userName));
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        return "user/user-homepage";
    }
}
