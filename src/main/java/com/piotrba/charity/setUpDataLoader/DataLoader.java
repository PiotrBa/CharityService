package com.piotrba.charity.setUpDataLoader;

import com.piotrba.charity.entity.Category;
import com.piotrba.charity.entity.Institution;
import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.CategoryRepository;
import com.piotrba.charity.repository.InstitutionRepository;
import com.piotrba.charity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner{

    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        User user = new User(null, "adam", passwordEncoder.encode("1234"), "Adam", "Smith", "adam.smith@example.com", "07234567890", "ROLE_ADMIN", true, new LinkedHashSet<>());
        userRepository.save(user);

        List<Institution> institutionList = Arrays.asList(
                new Institution(1L, "British Red Cross", "Humanitarian organization providing emergency assistance.", null),
                new Institution(2L, "Cancer Research UK", "Charity dedicated to cancer research.", null),
                new Institution(3L, "Great Ormond Street Hospital Charity", "Supporting a hospital for children's healthcare.", null),
                new Institution(4L, "RSPCA", "Organization working for animal welfare.", null),
                new Institution(5L, "Mind", "Charity focused on mental health support and care.", null)
        );
        institutionRepository.saveAll(institutionList);

        List<Category> categoryList = Arrays.asList(
                new Category(null, "Clothes", null),
                new Category(null, "Toys", null),
                new Category(null, "Books", null),
                new Category(null, "Electronics", null),
                new Category(null, "Household Items", null)
        );
        categoryRepository.saveAll(categoryList);
    }
}
