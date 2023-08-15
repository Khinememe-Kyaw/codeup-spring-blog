package com.codeup.codeupspringblog.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }
}