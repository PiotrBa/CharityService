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

import java.util.ArrayList;
import java.util.Arrays;
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
        List<User> usersList = Arrays.asList(
                new User(null, "adam", passwordEncoder.encode("1234"), "Adam", "Smith", "adam.smith@example.com", "07234567890", "ROLE_ADMIN", true, null, String.valueOf(new ArrayList<>())),
                new User(null, "emma", passwordEncoder.encode("1234"), "Emma", "Wath", "fiolecik220@gmail.com", "75123456234", "ROLE_USER", true, null, String.valueOf(new ArrayList<>()))
        );
        userRepository.saveAll(usersList);

        List<Institution> institutionList = Arrays.asList(
                new Institution(1L, "British Red Cross", "Humanitarian organization providing emergency assistance."),
                new Institution(2L, "Cancer Research UK", "Charity dedicated to cancer research."),
                new Institution(3L, "Great Ormond Street Hospital Charity", "Supporting a hospital for children's healthcare."),
                new Institution(4L, "RSPCA", "Organization working for animal welfare."),
                new Institution(5L, "Mind", "Charity focused on mental health support and care."),
                new Institution(6L, "The National Trust", "Charity focused on preserving historical places and natural beauty in the UK.")
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
