package com.piotrba.charity.security;

import com.piotrba.charity.entity.User;
import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import com.piotrba.charity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @GetMapping("/user")
    public String registerUserView(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        model.addAttribute("countAllDonations", donationService.countAllDonations());
        model.addAttribute("sumAllQuantities", donationService.sumAllQuantities());
        return "/security/register";
    }

    @PostMapping("/user")
    public String registerUser(User user){
        userService.registerUser(user);
        return "redirect:/login";
    }
}
