package com.piotrba.charity.viewController.admin;

import com.piotrba.charity.entity.Institution;
import com.piotrba.charity.repository.InstitutionRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/admin-profile-institutions")
@AllArgsConstructor
public class AdminInstitutionsController {

    private static final Logger logger = LoggerFactory.getLogger(AdminInstitutionsController.class);

    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final InstitutionService institutionService;

    @GetMapping()
    public String getAdminProfileView(Model model, Principal principal){
        logger.info("Accessing admin profile institutions page");
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "admin/institutions/admin-profile-institutions";
    }

    @GetMapping("/add")
    public String addInstitutionView(Model model, Principal principal){
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("institution", new Institution());
        return "admin/institutions/admin-profile-institutions-add";
    }

    @PostMapping("/add")
    public String addInstitution(Institution institution){
        logger.info("Adding new institution: {}", institution.getName());
        institutionService.saveInstitution(institution);
        return "redirect:/admin-profile-institutions";
    }

    @GetMapping("/update")
    public String editInstitutionView(Model model, @RequestParam Long id, Principal principal){
        logger.info("Editing institution with ID: {}", id);
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        Optional<Institution> optionalInstitution = institutionService.findInstitutionsById(id);
        if (optionalInstitution.isPresent()){
            model.addAttribute("institution", optionalInstitution.get());
        }
        return "admin/institutions/admin-profile-institutions-edit";
    }

    @PostMapping("/update")
    public String editInstitution(Institution institution, @RequestParam Long id){
        logger.info("Updating institution with ID: {}", id);
        institutionService.updateInstitution(id, institution);
        return "redirect:/admin-profile-institutions";
    }

    @GetMapping("/delete")
    public String deleteInstitutionView(Model model, @RequestParam Long id, Principal principal){
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        Optional<Institution> optionalInstitution = institutionService.findInstitutionsById(id);
        if (optionalInstitution.isPresent()){
            model.addAttribute("institution", optionalInstitution.get());
        }
        return "admin/institutions/admin-profile-institutions-delete";
    }

    @PostMapping("/delete")
    public String deleteInstitution(@RequestParam Long id){
        logger.info("Deleting institution with ID: {}", id);
        institutionService.deleteInstitution(id);
        return "redirect:/admin-profile-institutions";
    }
}
