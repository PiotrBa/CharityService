package com.piotrba.charity.security;

import com.piotrba.charity.service.DonationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final DonationService donationService;
    @GetMapping()
    public String getLoginView(Model model){
        model.addAttribute("sumAllQuantities", donationService.sumAllQuantities());
        model.addAttribute("countAllDonations", donationService.countAllDonations());
        return "security/login";
    }
}
