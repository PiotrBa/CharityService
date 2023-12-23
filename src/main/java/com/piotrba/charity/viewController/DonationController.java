package com.piotrba.charity.viewController;

import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.CategoryService;
import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    @GetMapping("/form")
    public String showForm(Model model, Principal principal){
        model.addAttribute("categoryList", categoryService.findAllCategories());
        model.addAttribute("institutionList", institutionService.findAllInstitutions());
        model.addAttribute("donation", new Donation());
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        return "form/donation-form";
    }

    @PostMapping("/form")
    public String saveForm(Principal principal, Donation donation, Model model){
        donationService.saveDonation(donation);
        String userName = principal.getName();
        if (userName != null) {
            User user = userRepository.getWithDonationsByUsername(userName);
            user.getUserDonations().add(donation);
            userRepository.save(user);
            model.addAttribute("loggedUser", userRepository.getByUsername(userName));
        }
        return "redirect:/form-confirmation";
    }
}
