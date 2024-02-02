package com.piotrba.charity.viewController.admin.categories;

import com.piotrba.charity.entity.Category;
import com.piotrba.charity.service.CategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/admin-profile-categories")
public class AdminInactiveCategoriesController {

    private static final Logger logger = LoggerFactory.getLogger(AdminInactiveCategoriesController.class);

    private final CategoryService categoryService;

    @GetMapping("/inactive")
    public String getInactiveCategoryView(Model model) {
        logger.info("Accessing inactive category view");
        model.addAttribute("categories", categoryService.findAllInactiveCategories());
        return "admin/categories/inactive/admin-profile-categories-inactive";
    }

    @GetMapping("/inactive/edit")
    public String editInactiveCategoryView(Model model, @RequestParam Long id) {
        logger.info("Accessing edit inactive category view for category ID: {}", id);
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("category", categoryOptional.get());
        }
        return "admin/categories/inactive/admin-profile-categories-inactive-edit";
    }

    @PostMapping("/inactive/edit")
    public String editInactiveCategory(Category category, @RequestParam Long id) {
        logger.info("Updating inactive category ID: {}", id);
        categoryService.updateCategory(id, category);
        return "redirect:/admin-profile-categories/inactive";
    }

    @GetMapping("/inactive/delete")
    public String deleteInactiveCategoryView(Model model, @RequestParam Long id) {
        logger.info("Accessing delete inactive category view for category ID: {}", id);
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("category", categoryOptional.get());
        }
        return "admin/categories/inactive/admin-profile-categories-inactive-delete";
    }

    @PostMapping("/inactive/delete")
    public String deleteInactiveCategory(@RequestParam Long id) {
        logger.info("Deleting inactive category ID: {}", id);
        categoryService.deleteCategory(id);
        return "redirect:/admin-profile-categories/inactive";
    }
}
