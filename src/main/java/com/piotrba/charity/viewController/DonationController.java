package com.piotrba.charity.viewController;

import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.CategoryService;
import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserRepository userRepository;

    @GetMapping("/form")
    public String showForm(Model model, Principal principal) {
        model.addAttribute("categoryList", categoryService.findAllCategories());
        model.addAttribute("institutionList", institutionService.findAllInstitutions());
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("donation", new Donation());
        return "form/donation-form";
    }

    @PostMapping("/form")
    public String saveForm(Donation donation, Principal principal) {
        String userName = principal.getName();
        if (userName != null) {
            Optional<User> userOptional = Optional.ofNullable(userRepository.getByUsername(userName));
            if (userOptional.isPresent()) {
                donation.setUser(userOptional.get());
                donationService.saveDonation(donation, userOptional.get().getId());
            } else {
                throw new RuntimeException("User not found");
            }
        }
        return "redirect:/form-confirmation";
    }
}
