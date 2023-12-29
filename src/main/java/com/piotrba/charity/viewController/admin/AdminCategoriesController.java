package com.piotrba.charity.viewController.admin;

import com.piotrba.charity.entity.Category;
import com.piotrba.charity.repository.CategoryRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.CategoryService;
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
@RequestMapping("/admin-profile-categories")
@AllArgsConstructor
public class AdminCategoriesController {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @GetMapping()
    public String getAdminProfileView(Model model, Principal principal){
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/categories/admin-profile-categories";
    }

    @GetMapping("/add")
    public String addCategoryView(Model model){
        model.addAttribute("category", new Category());
        return "admin/categories/admin-profile-categories-add";
    }

    @PostMapping("/add")
    public String addCategory(Category category){
        categoryService.saveCategory(category);
        return "redirect:/admin-profile-categories";
    }

    @GetMapping("/update")
    public String editCategoryView(Model model, @RequestParam Long id){
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()){
            model.addAttribute("category", categoryOptional.get());
        }
        return "/admin/categories/admin-profile-categories-edit";
    }

    @PostMapping("/update")
    public String editCategory(Category category, @RequestParam Long id){
        categoryService.updateCategory(id, category);
        return "redirect:/admin-profile-categories";
    }

    @GetMapping("/delete")
    public String deleteCategoryView(Model model, @RequestParam Long id){
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()){
            model.addAttribute("category", categoryOptional.get());
        }
        return "admin/categories/admin-profile-categories-delete";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam Long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin-profile-categories";
    }
}
