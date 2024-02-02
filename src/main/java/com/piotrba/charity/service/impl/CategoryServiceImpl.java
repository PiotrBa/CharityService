package com.piotrba.charity.service.impl;

import com.piotrba.charity.entity.Category;
import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.repository.CategoryRepository;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;

    @Override
    public List<Category> findAllActiveCategories() {
        return categoryRepository.findByActiveTrue();
    }

    @Override
    public List<Category> findAllInactiveCategories() {
        return categoryRepository.findByActiveFalse();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        category.setActive(true);
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> updateCategory(Long id, Category newCategory) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(newCategory.getName());
            category.setActive(newCategory.getActive());
            return Optional.of(categoryRepository.save(category));
        }
        return Optional.empty();
    }


    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Donation> donations = donationRepository.findByCategories_Id(id);
        for (Donation donation : donations) {
            donationRepository.delete(donation);
        }
        categoryRepository.delete(category);
    }

}
