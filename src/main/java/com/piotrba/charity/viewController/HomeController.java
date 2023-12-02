package com.piotrba.charity.viewController;

import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    @GetMapping
    public String homeAction(Model model){
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        model.addAttribute("sumAllQuantities", donationService.sumAllQuantities());
        model.addAttribute("countAllDonations", donationService.countAllDonations());
        return "index";
    }
}
