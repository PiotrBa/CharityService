package com.piotrba.charity.viewController.user;

import com.piotrba.charity.entity.Contact;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.DonationService;
import com.piotrba.charity.service.InstitutionService;
import com.piotrba.charity.service.impl.EmailSenderServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user-homepage")
@AllArgsConstructor
public class UserHomePageController {

    private static final Logger logger = LoggerFactory.getLogger(UserHomePageController.class);

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final EmailSenderServiceImpl emailSenderService;

    @GetMapping()
    public String getProfileView(Model model, Principal principal) {
        logger.info("Accessing user homepage");
        String userName = principal.getName();
        model.addAttribute("user", userRepository.getByUsername(userName));
        model.addAttribute("donationsToReceived", donationRepository.findDonationsByUserWithApprovalAndPackageNotReceived(userName));
        model.addAttribute("countUserDonations", donationRepository.countDonationsByUserUsername(principal.getName()));
        model.addAttribute("sumUserQuantities", donationRepository.sumQuantitiesByUserUsername(principal.getName()));
        model.addAttribute("institutionsList", institutionService.findAllActiveInstitutions());
        return "user/homepage/user-homepage";
    }

    @PostMapping("/package-received")
    public String packageReceived(@RequestParam Long id) {
        donationService.setPackageReceived(id);
        return "redirect:/user-homepage";
    }

    @PostMapping("/delete-donation")
    public String deleteDonation(@RequestParam Long id){
        donationService.deleteDonation(id);
        return "redirect:/user-homepage";
    }


    @PostMapping("/contact")
    public String sendContactMessage(Contact contactForm, Principal principal) {
        emailSenderService.sendLoginUserContactEmail(contactForm, principal);
        return "redirect:/user-homepage/contact-confirm";
    }


    @GetMapping("/contact-confirm")
    public String confirmSendContactMessageView(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("user", userRepository.getByUsername(userName));
        model.addAttribute("donationsToReceived", donationRepository.findDonationsByUserWithApprovalAndPackageNotReceived(userName));
        model.addAttribute("countDonations", donationRepository.countDonationsByUserUsername(userName));
        model.addAttribute("sumQuantities", donationRepository.sumQuantitiesByUserUsername(userName));
        model.addAttribute("institutionsList", institutionService.findAllActiveInstitutions());
        return "user/homepage/homepage-contact-confirm";
    }
}
