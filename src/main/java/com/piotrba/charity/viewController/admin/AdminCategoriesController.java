package com.piotrba.charity.viewController.admin;

import com.piotrba.charity.entity.Category;
import com.piotrba.charity.repository.CategoryRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.CategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/admin-profile-categories")
@AllArgsConstructor
public class AdminCategoriesController {

    private static final Logger logger = LoggerFactory.getLogger(AdminCategoriesController.class);

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @GetMapping()
    public String getAdminProfileView(Model model, Principal principal){
        logger.info("Loading admin categories view");
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/categories/admin-profile-categories";
    }

    @GetMapping("/add")
    public String addCategoryView(Model model, Principal principal){
        logger.info("Loading view to add a new category");
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("category", new Category());
        return "admin/categories/admin-profile-categories-add";
    }

    @PostMapping("/add")
    public String addCategory(Category category){
        logger.info("Adding new category: {}", category.getName());
        categoryService.saveCategory(category);
        return "redirect:/admin-profile-categories";
    }

    @GetMapping("/update")
    public String editCategoryView(Model model, @RequestParam Long id, Principal principal){
        logger.info("Loading view to edit category with id: {}", id);
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("category", categoryOptional.get());
        } else {
            logger.warn("Category with id {} not found", id);
        }
        return "admin/categories/admin-profile-categories-edit";
    }

    @PostMapping("/update")
    public String editCategory(Category category, @RequestParam Long id){
        logger.info("Updating category with id: {}", id);
        categoryService.updateCategory(id, category);
        return "redirect:/admin-profile-categories";
    }

    @GetMapping("/delete")
    public String deleteCategoryView(Model model, @RequestParam Long id, Principal principal){
        logger.info("Loading view to delete category with id: {}", id);
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("category", categoryOptional.get());
        } else {
            logger.warn("Category with id {} not found for deletion", id);
        }
        return "admin/categories/admin-profile-categories-delete";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam Long id){
        logger.info("Deleting category with id: {}", id);
        try {
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting category with id: {}", id, e);
        }
        return "redirect:/admin-profile-categories";
    }
}
