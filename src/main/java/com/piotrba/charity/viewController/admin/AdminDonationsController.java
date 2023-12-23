package com.piotrba.charity.viewController.admin;

import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin-profile-donations")
@AllArgsConstructor
public class AdminDonationsController {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;


    @GetMapping()
    public String showForm(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("user", userRepository.getByUsername(userName));
        model.addAttribute("donations", donationRepository.findAllWithUser());
        return "admin/donations/admin-profile-donations";
    }
}
