package com.piotrba.charity.viewController.admin;

import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.DonationRepository;
import com.piotrba.charity.repository.UserRepository;
import com.piotrba.charity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/admin-profile-users")
public class AdminUsersController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final DonationRepository donationRepository;


    @GetMapping()
    public String getAdminProfileView(Model model, Principal principal, User user){
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("donation", donationRepository.findByUserUsername(user.getUsername()));
        model.addAttribute("donations", donationRepository.findAll());
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
}
