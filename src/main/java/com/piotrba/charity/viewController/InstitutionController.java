package com.piotrba.charity.viewController;

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
@RequestMapping("/institution")
@AllArgsConstructor
public class InstitutionController {

    private static final Logger logger = LoggerFactory.getLogger(InstitutionController.class);

    private final InstitutionService institutionService;

    @GetMapping()
    public String getInstitutionView(Model model){
        logger.info("Accessing institution view");
        model.addAttribute("institution", institutionService.findAllInstitutions());
        return "/institution";
    }

    @GetMapping("/add")
    public String addInstitutionView(Model model){
        model.addAttribute("institution", new Institution());
        return "/institution";
    }

    @PostMapping("/add")
    public String addInstitution(Institution institution){
        institutionService.saveInstitution(institution);
        logger.info("Added new institution: {}", institution.getName());
        return "redirect:/institution";
    }

    @GetMapping("/edit")
    public String editInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> institutionOptional = institutionService.findInstitutionsById(id);
        if (institutionOptional.isPresent()){
            model.addAttribute("institution", institutionOptional.get());
        }
        return "/institution";
    }

    @PostMapping("/edit")
    public String editInstitution(Institution institution, @RequestParam Long id){
        institutionService.updateInstitution(id, institution);
        logger.info("Updated institution: {}", institution.getName());
        return "redirect:/institution";
    }

    @GetMapping("/delete")
    public String deleteInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> institutionOptional = institutionService.findInstitutionsById(id);
        if (institutionOptional.isPresent()){
            model.addAttribute("institution", institutionOptional.get());
        }
        return "institution";
    }

    @PostMapping("/delete")
    public String deleteInstitution(@RequestParam Long id){
        institutionService.deleteInstitution(id);
        logger.info("Deleted institution with id: {}", id);
        return "redirect:/institution";
    }
}
