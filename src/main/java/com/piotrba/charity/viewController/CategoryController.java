package com.piotrba.charity.viewController;

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
@RequestMapping("/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @GetMapping()
    public String getCategoryView(Model model) {
        logger.info("Accessing category view");
        model.addAttribute("category", categoryService.findAllCategories());
        return "/category";
    }

    @GetMapping("/add")
    public String addCategoryView(Model model) {
        logger.info("Accessing add category view");
        model.addAttribute("category", new Category());
        return "/category";
    }

    @PostMapping("/add")
    public String addCategory(Category category) {
        logger.info("Adding new category: {}", category.getName());
        categoryService.saveCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/edit")
    public String editCategoryView(Model model, @RequestParam Long id) {
        logger.info("Accessing edit category view for category ID: {}", id);
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("category", categoryOptional.get());
        }
        return "/category";
    }

    @PostMapping("/edit")
    public String editCategory(Category category, @RequestParam Long id) {
        logger.info("Updating category ID: {}", id);
        categoryService.updateCategory(id, category);
        return "redirect:/category";
    }

    @GetMapping("/delete")
    public String deleteCategoryView(Model model, @RequestParam Long id) {
        logger.info("Accessing delete category view for category ID: {}", id);
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("category", categoryOptional.get());
        }
        return "/category";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam Long id) {
        logger.info("Deleting category ID: {}", id);
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}
