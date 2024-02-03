package com.piotrba.charity.viewController.admin.institutions;

import com.piotrba.charity.entity.Institution;
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

import java.util.Optional;

@Controller
@RequestMapping("/admin-profile-institutions")
@AllArgsConstructor
public class AdminActiveInstitutionController {

    private static final Logger logger = LoggerFactory.getLogger(AdminActiveInstitutionController.class);

    private final InstitutionService institutionService;

    @GetMapping("/active")
    public String getActiveInstitutionView(Model model){
        logger.info("Accessing active institution view");
        model.addAttribute("institution", institutionService.findAllActiveInstitutions());
        return "admin/institutions/active/admin-profile-institutions";
    }

    @GetMapping("/active/add")
    public String addActiveInstitutionView(Model model){
        model.addAttribute("institution", new Institution());
        return "admin/institutions/active/admin-profile-institutions-add";
    }

    @PostMapping("/active/add")
    public String addActiveInstitution(Institution institution){
        institutionService.saveInstitution(institution);
        logger.info("Added new active institution: {}", institution.getName());
        return "redirect:/admin-profile-institutions/active";
    }

    @GetMapping("/active/edit")
    public String editActiveInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> institutionOptional = institutionService.findInstitutionsById(id);
        if (institutionOptional.isPresent()){
            model.addAttribute("institution", institutionOptional.get());
        }
        return "admin/institutions/active/admin-profile-institutions-edit";
    }

    @PostMapping("/active/edit")
    public String editActiveInstitution(Institution institution, @RequestParam Long id){
        institutionService.updateInstitution(id, institution);
        logger.info("Updated active institution: {}", institution.getName());
        return "redirect:/admin-profile-institutions/active";
    }

    @GetMapping("/active/delete")
    public String deleteActiveInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> institutionOptional = institutionService.findInstitutionsById(id);
        if (institutionOptional.isPresent()){
            model.addAttribute("institution", institutionOptional.get());
        }
        return "admin/institutions/active/admin-profile-institutions-delete";
    }

    @PostMapping("/active/delete")
    public String deleteInstitution(@RequestParam Long id){
        institutionService.deleteInstitution(id);
        logger.info("Deleted institution with id: {}", id);
        return "redirect:/admin-profile-institutions/active";
    }
}
