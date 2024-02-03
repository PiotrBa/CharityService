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
public class AdminInactiveInstitutionController {

    private static final Logger logger = LoggerFactory.getLogger(AdminActiveInstitutionController.class);

    private final InstitutionService institutionService;

    @GetMapping("/inactive")
    public String getInactiveInstitutionView(Model model){
        logger.info("Accessing inactive institution view");
        model.addAttribute("institution", institutionService.findAllInactiveInstitutions());
        return "/admin/institutions/inactive/admin-profile-institutions-inactive";
    }

    @GetMapping("/inactive/edit")
    public String editActiveInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> institutionOptional = institutionService.findInstitutionsById(id);
        if (institutionOptional.isPresent()){
            model.addAttribute("institution", institutionOptional.get());
        }
        return "/admin/institutions/inactive/admin-profile-institutions-inactive-edit";
    }

    @PostMapping("/inactive/edit")
    public String editActiveInstitution(Institution institution, @RequestParam Long id){
        institutionService.updateInstitution(id, institution);
        logger.info("Updated inactive institution: {}", institution.getName());
        return "redirect:/admin-profile-institutions/inactive";
    }

    @GetMapping("/inactive/delete")
    public String deleteActiveInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> institutionOptional = institutionService.findInstitutionsById(id);
        if (institutionOptional.isPresent()){
            model.addAttribute("institution", institutionOptional.get());
        }
        return "/admin/institutions/inactive/admin-profile-institutions-inactive-delete";
    }

    @PostMapping("/inactive/delete")
    public String deleteInstitution(@RequestParam Long id){
        institutionService.deleteInstitution(id);
        logger.info("Deleted inactive institution with id: {}", id);
        return "redirect:/admin-profile-institutions/inactive";
    }
}
