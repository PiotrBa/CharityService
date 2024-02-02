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
public class AdminActiveCategoriesController {

    private static final Logger logger = LoggerFactory.getLogger(AdminActiveCategoriesController.class);

    private final CategoryService categoryService;

    @GetMapping("/active")
    public String getActiveCategoryView(Model model) {
        logger.info("Accessing active category view");
        model.addAttribute("categories", categoryService.findAllActiveCategories());
        return "admin/categories/active/admin-profile-categories";
    }

    @GetMapping("/active/add")
    public String addActiveCategoryView(Model model) {
        logger.info("Accessing add category view");
        model.addAttribute("category", new Category());
        return "admin/categories/active/admin-profile-categories-add";
    }

    @PostMapping("/active/add")
    public String addActiveCategory(Category category) {
        logger.info("Adding new category: {}", category.getName());
        categoryService.saveCategory(category);
        return "redirect:/admin-profile-categories/active";
    }

    @GetMapping("/active/edit")
    public String editActiveCategoryView(Model model, @RequestParam Long id) {
        logger.info("Accessing edit category view for category ID: {}", id);
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("category", categoryOptional.get());
        }
        return "admin/categories/active/admin-profile-categories-edit";
    }

    @PostMapping("/active/edit")
    public String editActiveCategory(Category category, @RequestParam Long id) {
        logger.info("Updating category ID: {}", id);
        categoryService.updateCategory(id, category);
        return "redirect:/admin-profile-categories/active";
    }

    @GetMapping("/active/delete")
    public String deleteActiveCategoryView(Model model, @RequestParam Long id) {
        logger.info("Accessing delete category view for category ID: {}", id);
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("category", categoryOptional.get());
        }
        return "admin/categories/active/admin-profile-categories-delete";
    }

    @PostMapping("/active/delete")
    public String deleteActiveCategory(@RequestParam Long id) {
        logger.info("Deleting category ID: {}", id);
        categoryService.deleteCategory(id);
        return "redirect:/admin-profile-categories/active";
    }
}
