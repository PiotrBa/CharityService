package com.piotrba.charity.viewController;

import com.piotrba.charity.entity.Institution;
import com.piotrba.charity.service.InstitutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/institution")
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping()
    public String getInstitutionView(Model model){
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
        return "redirect:/institution";
    }

}
