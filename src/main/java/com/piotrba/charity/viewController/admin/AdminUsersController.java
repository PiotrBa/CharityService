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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/admin-profile-users")
public class AdminUsersController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final DonationRepository donationRepository;


    @GetMapping()
    public String getAdminProfileView(Model model, Principal principal){
        List<User> users = userRepository.findAll();
        Map<Long, Integer> userDonationsSum = new HashMap<>();
        for (User user : users) {
            Integer sum = Math.toIntExact(donationRepository.countDonationsByUserUsername(user.getUsername()));
            userDonationsSum.put(user.getId(), sum);
        }
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        model.addAttribute("users", users);
        model.addAttribute("userDonationsSum", userDonationsSum);
        return "admin/users/admin-profile-users";
    }

    @GetMapping("/update")
    public String editUserView(Model model, @RequestParam Long id, Principal principal){
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()){
            model.addAttribute("user", userOptional.get());
        }
        return "admin/users/admin-profile-users-edit";
    }

    @PostMapping("/update")
    public String editUser(User user, @RequestParam Long id){
        userService.updateUserByAdmin(id, user);
        return "redirect:/admin-profile-users";
    }

    @GetMapping("/delete")
    public String deleteUserView(Model model, @RequestParam Long id, Principal principal){
        model.addAttribute("user", userRepository.getByUsername(principal.getName()));
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()){
            model.addAttribute("user", userOptional.get());
        }
        return "admin/users/admin-profile-users-delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
        return "redirect:/admin-profile-users";
    }
}
