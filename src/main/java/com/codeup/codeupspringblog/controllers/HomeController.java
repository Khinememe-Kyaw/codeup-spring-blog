package com.codeup.codeupspringblog.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String landingpageGreeting(){
        return "This is the landing page";
    }

    @GetMapping("/home")
    public String welcome() {
        return "home";
    }

}
