package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Repositories.UserRepository;
import com.codeup.codeupspringblog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    UserRepository userRepository;

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){

        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        userRepository.save(user);
        return "redirect:/login";
    }

}