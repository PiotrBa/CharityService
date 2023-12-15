package com.piotrba.charity.viewController;

import com.piotrba.charity.entity.Category;
import com.piotrba.charity.entity.Institution;
import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.CategoryRepository;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.InstitutionRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.CategoryService;
import com.piotrba.charity.service.InstitutionService;
import com.piotrba.charity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/admin-profile")
public class AdminController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final InstitutionService institutionService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @GetMapping()
    public String getAdminProfileView(Model model, Principal principal, User user){
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("donation", donationRepository.findByUserUsername(user.getUsername()));
        model.addAttribute("donations", donationRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/admin-profile-users";
    }

    @GetMapping("/admin/add")
    public String addUserView(Model model){
        model.addAttribute("admin", new User());
        return "admin/admin-profile-users";
    }

    @PostMapping("/admin/add")
    public String addUser(User user){
        user.setRole("ROLE_ADMIN");
        userService.registerUser(user);
        return "redirect:/user/admin-profile-users";
    }

    @GetMapping("/user/update")
    public String editUserView(Model model, @RequestParam Long id){
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()){
            model.addAttribute("user", userOptional.get());
        }
        return "admin/admin-profile-users";
    }

    @PostMapping("/user/update")
    public String editUser(User user, @RequestParam Long id){
        userService.updateUser(id, user);
        return "redirect:/user/admin-profile-users";
    }

    @GetMapping("/user/delete")
    public String deleteUserView(Model model, @RequestParam Long id){
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()){
            model.addAttribute("user", userOptional.get());
        }
        return "admin/admin-profile-users";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
        return "redirect:/user/admin-profile-users";
    }


    //INSTITUTION
    @GetMapping("/institution/add")
    public String addInstitutionView(Model model){
        model.addAttribute("institution", new Institution());
        return "admin-profile-users";
    }

    @PostMapping("/institution/add")
    public String addInstitution(Institution institution){
        institutionService.saveInstitution(institution);
        return "redirect:user/admin-profile";
    }

    @GetMapping("/institution/update")
    public String editInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> optionalInstitution = institutionService.findInstitutionsById(id);
        if (optionalInstitution.isPresent()){
           model.addAttribute("institution", optionalInstitution.get());
        }
        return "admin-profile-users";
    }

    @PostMapping("/institution/update")
    public String editInstitution(Institution institution, @RequestParam Long id){
        institutionService.updateInstitution(id, institution);
        return "redirect:user/admin-profile";
    }

    @GetMapping("/institution/delete")
    public String deleteInstitutionView(Model model, @RequestParam Long id){
        Optional<Institution> optionalInstitution = institutionService.findInstitutionsById(id);
        if (optionalInstitution.isPresent()){
            model.addAttribute("institution", optionalInstitution.get());
        }
        return "admin-profile-users";
    }

    @PostMapping("/institution/delete")
    public String deleteInstitution(@RequestParam Long id){
        institutionService.deleteInstitution(id);
        return "redirect:user/admin-profile";
    }

    //CATEGORY
    @GetMapping("/category/add")
    public String addCategoryView(Model model){
        model.addAttribute("category", new Category());
        return "admin-profile-users";
    }

    @PostMapping("/category/add")
    public String addCategory(Category category){
        categoryService.saveCategory(category);
        return "redirect:user/admin-profile";
    }

    @GetMapping("/category/update")
    public String editCategoryView(Model model, @RequestParam Long id){
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()){
            model.addAttribute("category", categoryOptional.get());
        }
        return "admin-profile-users";
    }

    @PostMapping("/category/update")
    public String editCategory(Category category, @RequestParam Long id){
        categoryService.updateCategory(id, category);
        return "redirect:user/admin-profile";
    }

    @GetMapping("/category/delete")
    public String deleteCategoryView(Model model, @RequestParam Long id){
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()){
            model.addAttribute("category", categoryOptional.get());
        }
        return "admin-profile-users";
    }

    @PostMapping("/category/delete")
    public String deleteCategory(@RequestParam Long id){
        categoryService.deleteCategory(id);
        return "redirect:user/admin-profile";
    }


}
