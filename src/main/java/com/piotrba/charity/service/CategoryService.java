package com.piotrba.charity.service;

import com.piotrba.charity.entity.Category;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAllCategories();
    Optional<Category> findCategoryById(Long id);
    Category saveCategory(Category category);
    Optional<Category> updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}
