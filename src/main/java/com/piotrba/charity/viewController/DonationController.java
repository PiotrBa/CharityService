package com.piotrba.charity.viewController;

import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.service.CategoryService;
import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @GetMapping("/form")
    public String showForm(Model model){
        model.addAttribute("categoryList", categoryService.findAllCategories());
        model.addAttribute("institutionList", institutionService.findAllInstitutions());
        model.addAttribute("donation", new Donation());
        return "form/donation-form";
    }

    @PostMapping("/form")
    public String saveForm(Donation donation){
        donationService.saveDonation(donation);
        return "redirect:/form-confirmation";
    }
}
