package com.piotrba.charity.viewController.user;

import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user-homepage")
@AllArgsConstructor
public class UserHomePageController {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final UserService userService;

    @GetMapping()
    public String getProfileView(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("user", userRepository.getByUsername(userName));
        model.addAttribute("donations", donationRepository.findByUserUsername(userName));
        model.addAttribute("countDonations", donationRepository.countDonationsByUserUsername(userName));
        model.addAttribute("sumQuantities", donationRepository.sumQuantitiesByUserUsername(userName));
        return "user/user-homepage";
    }
}
