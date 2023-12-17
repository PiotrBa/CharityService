package com.piotrba.charity.viewController.admin;

import com.piotrba.charity.entity.Institution;
import com.piotrba.charity.repository.InstitutionRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
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

    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final InstitutionService institutionService;

    @GetMapping()
    public String getAdminProfileView(Model model, Principal principal){
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "admin/admin-profile-institutions";
    }

    @GetMapping("/institution/add")
    public String addInstitutionView(Model model){
        model.addAttribute("institution", new Institution());
        return "admin/admin-profile-institutions";
    }

    @PostMapping("/institution/add")
    public String addInstitution(Institution institution){
        institutionService.saveInstitution(institution);
        return "redirect:admin/admin-profile-institutions";
    }

    @GetMapping("/institution/update")
    public String editInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> optionalInstitution = institutionService.findInstitutionsById(id);
        if (optionalInstitution.isPresent()){
            model.addAttribute("institution", optionalInstitution.get());
        }
        return "admin/admin-profile-institutions";
    }

    @PostMapping("/institution/update")
    public String editInstitution(Institution institution, @RequestParam Long id){
        institutionService.updateInstitution(id, institution);
        return "redirect:admin/admin-profile-institutions";
    }

    @GetMapping("/institution/delete")
    public String deleteInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> optionalInstitution = institutionService.findInstitutionsById(id);
        if (optionalInstitution.isPresent()){
            model.addAttribute("institution", optionalInstitution.get());
        }
        return "admin/admin-profile-institutions";
    }

    @PostMapping("/institution/delete")
    public String deleteInstitution(@RequestParam Long id){
        institutionService.deleteInstitution(id);
        return "redirect:admin/admin-profile-institutions";
    }
}
