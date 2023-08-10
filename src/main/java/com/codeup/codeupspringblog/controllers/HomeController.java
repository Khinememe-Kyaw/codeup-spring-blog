package com.codeup.codeupspringblog.controllers;//package com.codeup.codeupspringblog.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//@Controller
//public class HomeController {
//    @GetMapping("/")
//    @ResponseBody
//    public String welcome(){
//        return "This is a landing page!";
//    }
//}


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String welcome() {
        return "home";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

}
