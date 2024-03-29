package com.piotrba.charity.viewController;

import com.piotrba.charity.entity.Contact;
import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.CategoryService;
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
import java.util.Optional;

@Controller
@RequestMapping("/form")
@AllArgsConstructor
public class DonationController {

    private static final Logger logger = LoggerFactory.getLogger(DonationController.class);

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserRepository userRepository;
    private final EmailSenderServiceImpl emailSenderService;


    @GetMapping()
    public String showForm(Model model, Principal principal) {
        logger.info("Accessing donation form");
        model.addAttribute("categoryList", categoryService.findAllActiveCategories());
        model.addAttribute("institutionList", institutionService.findAllActiveInstitutions());
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("donation", new Donation());
        return "/form/donation-form";
    }

    @PostMapping()
    public String saveForm(Donation donation, Principal principal) {
        logger.info("Saving donation form");
        String userName = principal.getName();
        if (userName != null) {
            Optional<User> userOptional = Optional.ofNullable(userRepository.getByUsername(userName));
            if (userOptional.isPresent()) {
                donation.setUser(userOptional.get());
                Donation saveDonation = donationService.saveDonation(donation, userOptional.get().getId());
                emailSenderService.sendConfirmationEmail(userOptional.get().getEmail(), saveDonation);
                logger.info("Donation saved for user: {}", userName);
            } else {
                logger.error("User not found: {}", userName);
                throw new RuntimeException("User not found");
            }
        }
        return "redirect:form/form-confirmation";
    }

    @GetMapping("/form-confirmation")
    public String donationConfirmation(Model model, Principal principal){
        logger.info("Accessing donation confirmation page");
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        return "form/form-confirmation";
    }

    @PostMapping("/donation-contact")
    public String sendContactMessage(Contact contactForm, Principal principal) {
        emailSenderService.sendLoginUserContactEmail(contactForm, principal);
        return "redirect:/form/donation-contact-confirm";
    }

    @GetMapping("/donation-contact-confirm")
    public String confirmSendContactMessageView(Model model, Principal principal){
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        return "form/donation-contact-confirm";
    }
}
