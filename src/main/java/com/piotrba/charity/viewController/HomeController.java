package com.piotrba.charity.viewController;

import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage")
@AllArgsConstructor
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final InstitutionService institutionService;
    private final DonationService donationService;

    @GetMapping
    public String homeAction(Model model){
        logger.info("Accessing home page");
        model.addAttribute("institutionsList", institutionService.findAllInstitutions());
        model.addAttribute("sumAllQuantities", donationService.sumAllQuantities());
        model.addAttribute("countAllDonations", donationService.countAllDonations());
        logger.info("Home page attributes set");
        return "index";
    }
}
