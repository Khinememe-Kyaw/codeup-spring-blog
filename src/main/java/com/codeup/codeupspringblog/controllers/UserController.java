package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Repositories.UserRepository;
import com.codeup.codeupspringblog.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@AllArgsConstructor
@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @GetMapping("/register")
    public String showSignupFormFromRegister() {
        return "redirect:/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/posts";
    }

    @GetMapping("/profile")
    public String showProfile() {return "users/profile";}
}
